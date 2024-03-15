package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Teleop.MoveArmCommand;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor1;
    private CANSparkMax intakeMotor2;
    public double intakeWheelRatio = 2.0/3.0; // ratio of smaller wheel to bigger wheel
    //public Boolean notePresent;
    public DigitalInput notePresentSwitch = new DigitalInput(0);

    public IntakeSubsystem(){
        intakeMotor1 = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless); // Smaller wheels
        intakeMotor1.setSmartCurrentLimit(30);
        intakeMotor1.setIdleMode(IdleMode.kCoast);

        intakeMotor2 = new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless); // Bigger wheels
        intakeMotor2.setSmartCurrentLimit(30);
        intakeMotor2.setIdleMode(IdleMode.kCoast);
    }

    public void spinIntake(double speed){
        intakeMotor1.set(speed);
        intakeMotor2.set(speed*intakeWheelRatio); // Derate larger wheel speed to match surface speed of smaller wheels
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Note Present Value", getNoteStatus());
    }


    public void collectNote(double speed){
        if(notePresentSwitch.get()){
            intakeMotor1.set(speed);
            intakeMotor2.set(speed*intakeWheelRatio);
        }
        else{
            intakeMotor1.set(0.0);
            intakeMotor2.set(0.0);
        }
    }

    public void dispenseNote(double speed){
        if(notePresentSwitch.get()){
            intakeMotor1.set(speed);
            intakeMotor2.set(speed*intakeWheelRatio);
        }
        else{
            intakeMotor1.set(0.0);
            intakeMotor2.set(0.0);
        }
    }

    public boolean getNoteStatus() {
        return notePresentSwitch.get();
    }
}
