package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class MoveClimberCommand extends Command {
    private final ClimberSubsystem m_climber; 
    public double climbSpeed; 

    public MoveClimberCommand(ClimberSubsystem climber, double speed){
        climbSpeed = speed; 
        m_climber = climber; 
        addRequirements(m_climber);
    }


    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        m_climber.climb(climbSpeed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}