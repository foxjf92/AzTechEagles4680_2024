package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor1; //Grab , place, and launch the note in amp or speaker
    private CANSparkMax intakeMotor2;
    public double intakeWheelRatio = 2.0/3.0; // ratio of smaller wheel to bigger wheel

    public IntakeSubsystem(){
        intakeMotor1 = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless); // Smaller wheels
        intakeMotor2 = new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless); // Bigger wheels
    }

    public void spinIntake(double speed){
        intakeMotor1.set(speed);
        intakeMotor2.set(speed*intakeWheelRatio);
    }
}
