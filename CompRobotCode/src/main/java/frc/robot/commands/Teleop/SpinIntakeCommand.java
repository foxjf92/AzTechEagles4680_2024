package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class SpinIntakeCommand extends Command {
    private final IntakeSubsystem m_intake; 
    public double intakeSpeed; 

    public SpinIntakeCommand(IntakeSubsystem intake, double speed){
        intakeSpeed = speed; 
        m_intake = intake; 
        addRequirements(m_intake);
    }


    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        m_intake.spinIntake(intakeSpeed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
