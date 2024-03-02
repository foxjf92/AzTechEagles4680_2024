package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class MoveArmCommand extends Command{
    private ArmSubsystem m_arm;
    public double armSetpoint; 
    private Timer timer = new Timer();
    private int position; //Symbolic elevator position where 1 = ground intake, etc.
    
    private PIDController m_armPID = new PIDController(m_arm.kP, m_arm.kI, m_arm.kD);

    public MoveArmCommand(ArmSubsystem arm, int m_position){
        position = m_position;
        m_arm = arm; 
        addRequirements(m_arm);
    }


    @Override
    public void initialize(){

    }

    @Override
    public void execute(){

        // Arm Setpoints - 1 = ground intake, 2 = amp, 3 = launch position
        if (position == 1) {
            armSetpoint = 1; // TODO Encoder Value
        }
        if (position == 2) {
            armSetpoint = 5; // TODO
        }
        if (position == 3) {
            armSetpoint = 6; // TODO
        }

        double controlEffort = m_arm.arbFF + m_armPID.calculate(m_arm.armEncoder.getPosition(), armSetpoint); // adds FF input to fight gravity

        m_arm.moveArm(controlEffort); 

        SmartDashboard.putNumber("Target Position", position);
        SmartDashboard.putNumber("Target Setpoint", armSetpoint);
        SmartDashboard.putNumber("PID Setpoint", m_armPID.getSetpoint());
        SmartDashboard.putNumber("PID Error", m_armPID.getPositionError());
        SmartDashboard.putNumber("Control Effort", controlEffort);
        }

    @Override
    public boolean isFinished(){
        return false;
    }
}
