package frc.robot.commands.Teleop;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;

public class MoveArmCommand extends Command{
    private final ArmSubsystem m_arm;
    private int position; //Symbolic arm position where 1 = ground intake, 2 = amp position, 3 = launch position
    public static double armSetpoint; // Encoder position value that corresponds to arm position

    public final double kP = 4.0;
    public final double kI = 0.0;
    public final double kD = 0.0;
    public final double arbFF = 0.025; 
    
    private PIDController m_armPID = new PIDController(kP,kI,kD);

    public MoveArmCommand(ArmSubsystem arm, int m_position){
        position = m_position;
        m_arm = arm; 
        addRequirements(m_arm);
    }


    @Override
    public void initialize(){

        if (position == 1) {
            armSetpoint = ArmConstants.intakePosition;
        }
        if (position == 2) {
            armSetpoint = ArmConstants.ampPosition;
        }
        if (position == 3) {
            armSetpoint = ArmConstants.launchPosition;
            // armSetpoint = ArmConstants.stagePosition;
        }
        // if (position == 4) {
        //     armSetpoint = ArmConstants.pushPosition;
        // }
    }

    @Override
    public void execute(){

        double controlEffort = arbFF - m_armPID.calculate(m_arm.armEncoder.getAbsolutePosition(), armSetpoint); // adds FF input to fight gravity, subtract PID output due to encoder inversion

        m_arm.moveArm(controlEffort);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
