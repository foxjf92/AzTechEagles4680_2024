package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeLaunchCommand extends Command {
    private final IntakeSubsystem m_intake; 
    public double intakeSpeed; 

    public IntakeLaunchCommand(IntakeSubsystem intake, double speed){
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
        //// TODO: I don't think we care about this command ending
        // if(m_intake.getNoteStatus()){
        //     return true;
        // }
        return false;
    }
}
