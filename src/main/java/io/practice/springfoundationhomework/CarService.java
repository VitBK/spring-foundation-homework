package io.practice.springfoundationhomework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Transactional(readOnly = true)
    public Car getCarById(Integer id) {
        return carRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }
}
