package nextstep.ref;

import nextstep.ref.ex1.MaintenanceJob;
import nextstep.ref.ex1.PaintJob;
import nextstep.ref.ex1.PlatinumJobCard;
import nextstep.ref.ex1.RepairJob;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

public class ConstructorByClassNameTest {
    @DisplayName("")
    @Test
    void setJobTypeByString() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PlatinumJobCard<RepairJob> platinumJobCard1 = new PlatinumJobCard<>();
        platinumJobCard1.setJobType("nextstep.ref.ex1.RepairJob");
        assertThat(platinumJobCard1.startJob()).isEqualTo("Start Platinum Repair Job");

        PlatinumJobCard<PaintJob> platinumJobCard2 = new PlatinumJobCard<>();
        platinumJobCard2.setJobType("nextstep.ref.ex1.PaintJob");
        assertThat(platinumJobCard2.startJob()).isEqualTo("Start Platinum Paint Job");

        PlatinumJobCard<MaintenanceJob> platinumJobCard3 = new PlatinumJobCard<>();
        platinumJobCard3.setJobType("nextstep.ref.ex1.MaintenanceJob");
        assertThat(platinumJobCard3.startJob()).isEqualTo("Start Platinum Maintenance Job");
    }

    @DisplayName("")
    @Test
    void classNotFoundExceptionTest() {
        PlatinumJobCard<RepairJob> platinumJobCard = new PlatinumJobCard<>();
        Assertions.assertThatThrownBy(() -> platinumJobCard.setJobType("nextstep.ref.ex1.Example"))
                .isInstanceOf(ClassNotFoundException.class);
    }

    @DisplayName("")
    @Test
    void setJobTypeClass() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PlatinumJobCard<RepairJob> platinumJobCard1 = new PlatinumJobCard<>();
        platinumJobCard1.setJobType(RepairJob.class);
        assertThat(platinumJobCard1.startJob()).isEqualTo("Start Platinum Repair Job");

        PlatinumJobCard<PaintJob> platinumJobCard2 = new PlatinumJobCard<>();
        platinumJobCard2.setJobType(PaintJob.class);
        assertThat(platinumJobCard2.startJob()).isEqualTo("Start Platinum Paint Job");

        PlatinumJobCard<MaintenanceJob> platinumJobCard3 = new PlatinumJobCard<>();
        platinumJobCard3.setJobType(MaintenanceJob.class);
        assertThat(platinumJobCard3.startJob()).isEqualTo("Start Platinum Maintenance Job");
    }
}
