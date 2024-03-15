// package frc.robot.commands.Autons;

// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.Constants;
// import frc.robot.RobotContainer;
// import frc.robot.Constants.ArmConstants;
// import frc.robot.commands.Teleop.MoveArmCommand;
// import frc.robot.subsystems.ArmSubsystem;
// import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.LauncherSubsystem;
// import swervelib.SwerveDrive;

// public class TwoNoteAutoCommand extends SequentialCommandGroup{


//     private final LauncherSubsystem m_launcher;
//     private final IntakeSubsystem m_intake;
//     private final ArmSubsystem m_arm;
//     private final SwerveDrive m_swerveDrive;
    
//     public void OneNoteAutoCommand( LauncherSubsystem launcher, IntakeSubsystem intake, ArmSubsystem arm, SwerveDrive swerveDrive){
//         m_launcher = launcher;
//         m_intake = intake;
//         m_arm = arm;
//         m_swerveDrive = swerveDrive;

//         addCommands(new MoveArmCommand(arm, 3));
//         addCommands(null);
        
//     }
// }
