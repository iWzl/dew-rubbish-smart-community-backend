import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterRequest;
import com.upuphub.dew.community.machine.DewMachineApplication;
import com.upuphub.dew.community.machine.controller.MatchineController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DewMachineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MachineServiceTest {
    @Autowired
    MatchineController matchineController;

    @Test
    public void helloIotDATest() {
        MachineRegisterRequest machineRegisterRequest = MachineRegisterRequest.newBuilder()
                .setMachineType(1)
                .setMachineMacAddress("MAC-MACHINE-ADDRESS")
                .setMachineMaker("Power By LeoWang")
                .setMachineVersion("LDG-0001")
                .setMachineName("Dew智能垃圾桶")
                .setBindKey(UUID.randomUUID().toString().substring(0,8))
                .build();
        matchineController.registerNewMachineInfo(machineRegisterRequest);
    }
}
