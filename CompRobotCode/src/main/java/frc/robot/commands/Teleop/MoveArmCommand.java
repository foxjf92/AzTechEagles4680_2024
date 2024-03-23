package frc.robot.commands.Teleop;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;

public class MoveArmCommand extends Command{
    private final ArmSubsystem m_arm;
    private int position; //Symbolic arm position where 1 = ground intake, 2 = amp position, 3 = launch position
    public static double armSetpoint; // Encoder position value that corresponds to arm position

    
    // public final double kP = 0.04; // TODO - Tune this second
    // public final double kI = 0.0; // TODO - Tune this fourth
    // public final double kD = 0.005; // TODO - Tune this third
    // public final double arbFF = 0.0014; //TODO - Tune this first

    public final double kP = 4.0; // TODO - Tune this second
    public final double kI = 0.0; // TODO - Tune this fourth
    public final double kD = 0.0; // TODO - Tune this third
    public final double arbFF = 0.025; //TODO - Tune this first


   // public SlewRateLimiter armFilter = new SlewRateLimiter(0.1);
    //public double filterValue;
    
    private PIDController m_armPID = new PIDController(kP,kI,kD);

    public MoveArmCommand(ArmSubsystem arm, int m_position){
        position = m_position;
        m_arm = arm; 
        addRequirements(m_arm);
    }


    @Override
    public void initialize(){

        // Arm Setpoints - 1 = ground intake, 2 = amp, 3 = launch position, 0'd off of ground level
        // if (position == 1) {
        //     armSetpoint = 2.0;
        // }
        // if (position == 2) {
        //     armSetpoint = 53.8;
        // }
        // if (position == 3) {
        //     armSetpoint = 58.5;
        // }

        if (position == 1) {
            armSetpoint = ArmConstants.intakePosition;
        }
        if (position == 2) {
            armSetpoint = ArmConstants.ampPosition;
        }
        if (position == 3) {
            armSetpoint = ArmConstants.launchPosition;
        }
    }

    @Override
    public void execute(){

        double controlEffort = arbFF - m_armPID.calculate(m_arm.armEncoder.getAbsolutePosition(), armSetpoint); // adds FF input to fight gravity

        //filterValue = armFilter.calculate(controlEffort);
        //controlEffort = filterValue;

        m_arm.moveArm(controlEffort); 

        //SmartDashboard.putNumber("PID Error", m_armPID.getPositionError());
        //SmartDashboard.putNumber("Control Effort", controlEffort);

        }

    @Override
    public boolean isFinished(){
        return false;
    }
}
