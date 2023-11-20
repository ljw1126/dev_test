package nextstep.ref.ex1;

import java.lang.reflect.InvocationTargetException;

public class PlatinumJobCard<T extends Job>{
    private T jobType;

    public void setJobType(String clazzName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<T> clazz = (Class<T>) Class.forName(clazzName);
        this.jobType = clazz.getDeclaredConstructor().newInstance();
    }

    public void setJobType(Class<T> jobTypeClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.jobType = jobTypeClass.getDeclaredConstructor().newInstance();
    }

    public String startJob() {
        return "Start Platinum " + this.jobType.getJobType();
    }
}
