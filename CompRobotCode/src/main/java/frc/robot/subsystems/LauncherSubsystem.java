package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {
    
    private CANSparkMax launcherMotor1; //determine if right or left motor
    private CANSparkMax launcherMotor2; //determine if right or left motor

    //public double launchSpeed;
   
    public LauncherSubsystem(){
        launcherMotor1 = new CANSparkMax(12, CANSparkLowLevel.MotorType.kBrushless);
        launcherMotor1.setSmartCurrentLimit(80);
        launcherMotor1.setIdleMode(IdleMode.kBrake);

        launcherMotor2 = new CANSparkMax(13, CANSparkLowLevel.MotorType.kBrushless);
        launcherMotor2.setSmartCurrentLimit(80);
        launcherMotor2.setIdleMode(IdleMode.kBrake);
    }

    public void launch(double launchSpeed) {
        launcherMotor1.set(launchSpeed);
        launcherMotor2.set(-launchSpeed);
    }

}