package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    
    private CANSparkMax armMotor1; //Move to a certain position
    public double armspeed; 


    public ArmSubsystem(){
        armMotor1 = new CANSparkMax (9, CANSparkLowLevel.MotorType.kBrushless);
    }

    public void moveArm(double armspeed) {
    armMotor1.set(armspeed);
    }

}

