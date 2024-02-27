package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor1; //Grab , place, and launch the note in amp or speaker
    private CANSparkMax intakeMotor2;

    public IntakeSubsystem(){
        intakeMotor1 = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);
        intakeMotor2 = new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless);
    }

    public void intakeGamepiece(){
        intakeMotor1.set(0);
        intakeMotor2.set(0);
    }

    public void scoreAmp(){
        intakeMotor1.set(0);
        intakeMotor2.set(0);
    }

    public void insertGamePiece(){
        intakeMotor1.set(0);
        intakeMotor2.set(0);
    }
}
