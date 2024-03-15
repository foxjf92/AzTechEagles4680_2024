package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkBase.IdleMode;
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
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Climb Sensor Value",climberEncoder.getPosition());
  }

  public void climb(double speed) {
    climberMotor.set(speed);
    SmartDashboard.putNumber("Climber", speed);
  }
 
}