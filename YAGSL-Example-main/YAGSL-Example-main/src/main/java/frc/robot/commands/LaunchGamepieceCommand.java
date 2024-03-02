package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchGamepieceCommand extends Command{
    private final LauncherSubsystem m_launcher;
    public double launchSpeed; 

    public LaunchGamepieceCommand(LauncherSubsystem launcher, double speed){
        m_launcher = launcher;
        launchSpeed = speed; 
        addRequirements(m_launcher);
    }
    

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        m_launcher.launch(launchSpeed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
