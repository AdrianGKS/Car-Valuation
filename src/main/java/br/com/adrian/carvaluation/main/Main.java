package br.com.adrian.carvaluation.main;

import br.com.adrian.carvaluation.model.DetailedVehicle;
import br.com.adrian.carvaluation.model.Vehicle;
import br.com.adrian.carvaluation.model.VehicleModels;
import br.com.adrian.carvaluation.service.APIConsume;
import br.com.adrian.carvaluation.service.ConvertData;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner in = new Scanner(System.in);
    private final APIConsume apiConsume = new APIConsume();
    private final ConvertData convert = new ConvertData();
    private static final String ADDRESS = "https://www.parallelum.com.br/fipe/api/v1/";

    public void menu() throws JsonProcessingException {
        System.out.println("""
                Escolha o tipo de veículo que você quer consultar:
                Carro
                Moto
                Caminhão

                Digite uma das opções listadas acima:\s""");
        var option = in.nextLine();

        String address;
        if (option.toLowerCase().contains("carr")) {
           address = ADDRESS + "carros/marcas";
        }
        else if (option.toLowerCase().contains("mot")) {
            address = ADDRESS + "motos/marcas";
        }
        else {
            address = ADDRESS + "caminhaoes/marcas";
        }

        var json = apiConsume.getData(address);

        List<Vehicle> vehicles = convert.getList(json, Vehicle.class);
        vehicles.stream()
                .sorted(Comparator.comparing(Vehicle::code))
                .forEach(System.out::println);

        System.out.println("\nInforme o código da marca que você quer consultar: ");
        var code = in.nextLine();

        address += "/" + code + "/modelos";
        json = apiConsume.getData(address);
        VehicleModels vehicleModels = convert.getData(json, VehicleModels.class);
        vehicleModels.vehicles().stream()
                .sorted(Comparator.comparing(Vehicle::code))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do veículo que você deseja encontrar: ");
        var name = in.nextLine();

        List<Vehicle> filterModels = vehicleModels.vehicles().stream()
                .filter(v -> v.name().toLowerCase().contains(name.toLowerCase()))
                .toList();

        filterModels.forEach(System.out::println);

        System.out.println("\nDigite o código do veículo para acharmos os valores: ");
        var modelCode = in.nextLine();

        address += "/" + modelCode + "/anos";
        json = apiConsume.getData(address);

        List<Vehicle> years = convert.getList(json, Vehicle.class);
        List<DetailedVehicle> detailedVehicles = new ArrayList<>();

        for (Vehicle year : years) {
            var addressYears = address + "/" + year.code();
            json = apiConsume.getData(addressYears);
            DetailedVehicle vehicle = convert.getData(json, DetailedVehicle.class);
            detailedVehicles.add(vehicle);
        }

        System.out.println("\nModelos filtrados: ");
        detailedVehicles.forEach(System.out::println);
    }
}
