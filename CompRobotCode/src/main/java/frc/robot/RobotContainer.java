// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Teleop.AbsoluteDriveAdv;
import frc.robot.commands.Teleop.LaunchGamepieceCommand;
import frc.robot.commands.Teleop.MoveArmCommand;
import frc.robot.commands.Teleop.MoveClimberCommand;
import frc.robot.commands.Teleop.SpinIntakeCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {                                                                    
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                        "swerve"));

  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final ArmSubsystem arm = new ArmSubsystem();
  private final LauncherSubsystem launcher = new LauncherSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  
  //CommandJoystick driverController = new CommandJoystick(1);
  CommandXboxController driverXbox = new CommandXboxController(0);
  CommandXboxController operatorXbox = new CommandXboxController(1);

  // Intake function commands
  Command intakeStill = new SpinIntakeCommand(intake, 0);
  Command intakeCollect = new SpinIntakeCommand(intake, -0.5);
  Command intakeAmp = new SpinIntakeCommand(intake, 0.5);
  Command intakeLaunch = new SpinIntakeCommand(intake, -1.0);

  // Arm position
  Command armIntake = new MoveArmCommand(arm, 1);
  Command armAmp = new MoveArmCommand(arm, 2);
  Command armLaunch = new MoveArmCommand(arm, 3);

  WaitCommand launchDelay = new WaitCommand(0.5);
    
  Command launchGamepiece = new LaunchGamepieceCommand(launcher, -1.0);
  Command launchStill = new LaunchGamepieceCommand(launcher, 0);

  // Climber controls
  Command climberExtend = new MoveClimberCommand(climber, 0.1);
  Command climberRetract = new MoveClimberCommand(climber, -0.1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                OperatorConstants.LEFT_X_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                OperatorConstants.RIGHT_X_DEADBAND),
                                                                   driverXbox.getHID()::getYButtonPressed,
                                                                   driverXbox.getHID()::getAButtonPressed,
                                                                   driverXbox.getHID()::getXButtonPressed,
                                                                   driverXbox.getHID()::getBButtonPressed);
    
    drivebase.setDefaultCommand(closedAbsoluteDriveAdv);
    
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

     
    // new JoystickButton(driverXbox, 5).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
    // new JoystickButton(driverXbox,
    //                    2).whileTrue(
    //     Commands.deferredProxy(() -> drivebase.driveToPose(
    //                                new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
    //                           ));
  //  new JoystickButton(driverXbox, 3).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));

    driverXbox.rightBumper().onTrue(new InstantCommand(drivebase::zeroGyro));
    driverXbox.rightTrigger().whileTrue(climberExtend);
    driverXbox.leftTrigger().whileTrue(climberRetract);

    
    operatorXbox.a().onTrue(armIntake);
    operatorXbox.x().onTrue(armAmp);
    operatorXbox.y().onTrue(armLaunch);
    operatorXbox.leftBumper().whileTrue(intakeCollect);
    operatorXbox.rightBumper().whileTrue(intakeAmp);
    operatorXbox.rightTrigger().whileTrue(launchGamepiece.alongWith(launchDelay.andThen(intakeLaunch)));


    // driverXbox.x().onTrue(Commands.runOnce(drivebase::addFakeVisionReading));
    // driverXbox.b().whileTrue(
    //     Commands.deferredProxy(() -> drivebase.driveToPose(
    //                                new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
    //                           ));
    // driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return drivebase.getAutonomousCommand("New Auto");
  }
}
