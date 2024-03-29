package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class MoveArmCommand extends Command{
    private final ArmSubsystem m_arm;
    public static double armSetpoint; 
    //private Timer timer = new Timer();
    private int position; //Symbolic elevator position where 1 = ground intake, 2 = amp position, 3 = launch position
    
    public final double kP = 0.05; // TODO - Tune this second
    public final double kI = 0.0; // TODO - Tune this fourth
    public final double kD = 0.005; // TODO - Tune this third
    public final double arbFF = 0.0007; //TODO - Tune this first

    private PIDController m_armPID = new PIDController(kP,kI,kD);

    public MoveArmCommand(ArmSubsystem arm, int m_position){
        position = m_position;
        m_arm = arm; 
        addRequirements(m_arm);
    }


    @Override
    public void initialize(){

        // Arm Setpoints - 1 = ground intake, 2 = amp, 3 = launch position
        if (position == 1) {
            armSetpoint = 3.0; // TODO Encoder Value for ground intake
        }
        if (position == 2) {
            armSetpoint = 25.0; // TODO Encoder value for amp score
        }
        if (position == 3) {
            armSetpoint = 56.0; // TODO encoder value for launch
        }

    }

    @Override
    public void execute(){

        double controlEffort = arbFF + m_armPID.calculate(m_arm.armEncoder.getPosition(), armSetpoint); // adds FF input to fight gravity

        m_arm.moveArm(controlEffort); 

        SmartDashboard.putNumber("Target Position", position);
        SmartDashboard.putNumber("Target Setpoint", armSetpoint);
        SmartDashboard.putNumber("PID Setpoint", m_armPID.getSetpoint());
        SmartDashboard.putNumber("PID Error", m_armPID.getPositionError());
        SmartDashboard.putNumber("Control Effort", controlEffort);
        
        // SmartDashboard.putNumber("Target Position", 1.0);
        // SmartDashboard.putNumber("Target Setpoint", 1.1);
        // SmartDashboard.putNumber("PID Setpoint", 1.2);
        // SmartDashboard.putNumber("PID Error", 1.3);
        // SmartDashboard.putNumber("Control Effort", 1.4);

        }

    @Override
    public boolean isFinished(){
        return false;
    }
}
