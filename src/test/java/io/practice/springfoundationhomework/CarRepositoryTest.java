package io.practice.springfoundationhomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarRepositoryTest {

    public static final Car TEST_CAR = new Car(null, "Toyota", "White");

    @Autowired
    private CarRepository carRepository;

    @Test
    void saveCarTest() {
        //given
        Car car = new Car(null, "Toyota", "White");

        //when
        Car savedCar = carRepository.save(car);

        //then
        Assertions.assertNotNull(savedCar);
        Assertions.assertNotNull(savedCar.getId());
    }

    @Test
    void findCarByIdTest() {
        //given
        Car savedCar = carRepository.save(TEST_CAR);

        //when
        Car retrievedCar = carRepository.findById(savedCar.getId()).orElse(null);

        //then
        Assertions.assertNotNull(retrievedCar);
    }

    @Test
    void deleteByIdTest() {
        //given
        Car car = new Car(null, "Toyota", "White");
        Car savedCar = carRepository.save(car);

        //when
        carRepository.deleteById(savedCar.getId());

        //then
        Assertions.assertEquals(0, carRepository.findAll().size());
    }

}
