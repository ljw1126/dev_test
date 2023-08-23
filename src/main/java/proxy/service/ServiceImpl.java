package proxy.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements ServiceInterface {
    @Override
    public void save() {
        System.out.println("save 실행");
    }

    @Override
    public void get() {
        System.out.println("get 실행");
    }
}
