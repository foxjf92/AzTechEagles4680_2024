package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {

    private CANSparkMax climberMotor;
    public RelativeEncoder climberEncoder;

  public ClimberSubsystem() {
        climberMotor = new CANSparkMax (14, CANSparkLowLevel.MotorType.kBrushless);
        climberMotor.setSmartCurrentLimit(40);
        climberMotor.setIdleMode(IdleMode.kBrake);
        climberMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
        climberMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
        climberMotor.setSoftLimit(SoftLimitDirection.kForward, 235);
        climberMotor.setSoftLimit(SoftLimitDirection.kReverse, -310);

       climberEncoder = climberMotor.getEncoder(); 

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Climb Sensor Value",climberEncoder.getPosition());
  }

  public void climb(double speed) {
    climberMotor.set(speed);
  }
 
}