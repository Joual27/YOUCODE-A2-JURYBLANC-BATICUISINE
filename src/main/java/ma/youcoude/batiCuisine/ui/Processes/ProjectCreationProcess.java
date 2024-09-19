package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.material.MaterialService;
import ma.youcoude.batiCuisine.component.material.interfaces.MaterialServiceI;
import ma.youcoude.batiCuisine.component.workforce.WorkForceService;
import ma.youcoude.batiCuisine.component.workforce.Workforce;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceServiceI;
import ma.youcoude.batiCuisine.customer.Customer;
import ma.youcoude.batiCuisine.customer.CustomerService;
import ma.youcoude.batiCuisine.customer.interfaces.CustomerServiceI;
import ma.youcoude.batiCuisine.enums.ProjectStatus;
import ma.youcoude.batiCuisine.exceptions.CustomerNotFoundException;
import ma.youcoude.batiCuisine.project.Project;
import ma.youcoude.batiCuisine.utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectCreationProcess {
    private final Scanner scanner ;
    private final CustomerServiceI customerService;
    private final MaterialServiceI materialService;
    private final WorkForceServiceI workforceService;
    private Project projectData = new Project();

    public ProjectCreationProcess() {
        scanner = new Scanner(System.in);
        customerService = new CustomerService();
        materialService = new MaterialService();
        workforceService = new WorkForceService();
    }


    public void handleFullProjectCreationProcess() {
        handleAssociatingCustomerToProject();
        System.out.println("----------------Creating Project -------------\n");
        System.out.println("Enter the name of the project: ");
        String projectName = scanner.nextLine();
        projectData.setProjectName(projectName);
        projectData.setProjectStatus(ProjectStatus.ONGOING);
        handleAddingMaterialProcess();
        handleAddingWorkForceProcess();
    }

    public void handleAssociatingCustomerToProject() {
       while (true) {
           System.out.println("----------------Associating customer to project -------------\n");
           System.out.println("1 - Search for existing Customer :");
           System.out.println("2 - Add New Customer :");
           int choice = scanner.nextInt();
           scanner.nextLine();
           if(choice == 1) {
               handleSearchingForExistingCustomer();
               break;
           }
           else if(choice == 2) {
               handleAddingNewCustomer();
               break;
           }
           else {
               System.out.println("Invalid choice , TRY AGAIN !");
           }
       }

    }

    public void handleSearchingForExistingCustomer() {
        System.out.println("-------Searching For Customer--------\n");
        System.out.println("Please enter the Full Name of the customer: ");
        String fullName = scanner.nextLine().trim();
        Customer existingCustomer = new Customer();
        try{
            existingCustomer = customerService.getCustomerByFullName(fullName);
            System.out.println("Customer found !");
            System.out.println("Full Name : " + existingCustomer.getFullName());
            System.out.println("Address : " + existingCustomer.getAddress());
            System.out.println("Phone Number : " + existingCustomer.getPhoneNumber());

            while (true){
                System.out.println("Do you wanna associate the project to "+existingCustomer.getFullName()+" ? (Y/N)");
                String choice = scanner.nextLine().trim();
                if(choice.equalsIgnoreCase("Y")) {
                    projectData.setCustomer(existingCustomer);
                    break;
                }
                else if(choice.equalsIgnoreCase("N")) {
                    break;
                }
                else {
                    System.out.println("Invalid choice , TRY AGAIN !");
                }
            }
        }
        catch(CustomerNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void handleAddingNewCustomer() {
        System.out.println("-------Adding New Customer--------\n");
        System.out.println("Please enter the Full Name of the customer: ");
        String fullName = scanner.nextLine().trim();
        System.out.println("Please enter customer's address :");
        String address = scanner.nextLine().trim();
        String phoneNumber;
        while(true){
            System.out.println("Please enter customer's phone number: ");
            phoneNumber = scanner.nextLine().trim();
            if(Validator.validatePhoneNumber(phoneNumber)){
                break;
            }
            else{
                System.out.println("Invalid phone number, TRY AGAIN!");
            }
        }
        boolean isPro = handleIsProfessionalValue();
        Customer newCustomer = new Customer();
        newCustomer.setFullName(fullName);
        newCustomer.setAddress(address);
        newCustomer.setPhoneNumber(phoneNumber);
        newCustomer.setProfessional(isPro);

        Customer createdCustomer = customerService.saveCustomer(newCustomer);
        System.out.println("Customer " + createdCustomer.getFullName() + " Created Successfully!");
        projectData.setCustomer(createdCustomer);
    }


    public boolean handleIsProfessionalValue(){
        while(true){
            System.out.println("Is this Customer professional ?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            int choice = scanner.nextInt();
            if(choice == 1){
                return true;
            }
            else if(choice == 2){
                return false;
            }
            else{
                System.out.println("Invalid choice, TRY AGAIN!");
            }
        }
    }


    public void handleAddingMaterialProcess(){
        List<Component> components = new ArrayList<>();
        while(true){
            System.out.println("-------Adding Materials--------\n");
            System.out.println("Enter the Material Name :");
            String materialName = scanner.nextLine().trim();
            int quantity;
            while(true){
                System.out.println("Enter the Needed Quantity :");
                quantity = scanner.nextInt();
                scanner.nextLine();
                if (quantity > 0){
                    break;
                }
                else {
                    System.out.println("can't be negative value! TRY AGAIN !");
                }
            }
            double pricePerUnit;
            while(true){
                System.out.println("Enter the Price Per unit :");
                pricePerUnit = scanner.nextDouble();
                scanner.nextLine();
                if (pricePerUnit > 0){
                    break;
                }
                else {
                    System.out.println("can't be negative value! TRY AGAIN !");
                }
            }
            double transportationCost;
            while (true){
                System.out.println("Enter the Transportation Cost :");
                transportationCost = scanner.nextDouble();
                scanner.nextLine();
                if (transportationCost > 0){
                    break;
                }
                else {
                    System.out.println("can't be negative value! TRY AGAIN !");
                }
            }
            double qualityCoefficient;
            while(true){
                System.out.println("Enter the quality coefficient of this material: ");
                qualityCoefficient = scanner.nextDouble();
                scanner.nextLine();
                if (Validator.validateCoefficients(qualityCoefficient)){
                    break;
                }
                else {
                    System.out.println("quality coefficient should be between 1 and 2");
                }
            }

            Material material = new Material();
            material.setComponentName(materialName);
            material.setQuantity(quantity);
            material.setPricePerUnit(pricePerUnit);
            material.setTransportationCost(transportationCost);
            material.setQualityCoefficient(qualityCoefficient);

            components.add(material);

            System.out.println("Material " + materialName + " Added Successfully!");
            System.out.println("Do you want to add another Material ? (Y/N)");
            String choice = scanner.nextLine().trim();

            if(choice.equalsIgnoreCase("N")){
                projectData.setComponents(components);
                break;
            }
        }
    }

    public void handleAddingWorkForceProcess(){
        List<Component> components = projectData.getComponents();
        while(true){
            System.out.println("-------Adding Work Force Process-------\n");
            System.out.println("Enter the Workforce Name :");
            String workforceName = scanner.nextLine().trim();
            double hourlyRate;
            while(true){
                System.out.println("Enter the Hourly Rate :");
                hourlyRate = scanner.nextDouble();
                scanner.nextLine();
                if (hourlyRate > 0){
                    break;
                }
                else {
                    System.out.println("can't be negative value! TRY AGAIN!");
                }
            }
            int workHours;
            while(true){
                System.out.println("Enter the WorkHours :");
                workHours = scanner.nextInt();
                scanner.nextLine();
                if (workHours > 0){
                    break;
                }
                else {
                    System.out.println("can't be negative value! TRY AGAIN!");
                }
            }
            double workerProductivityCoefficient;

            while(true){
                System.out.println("Enter the productivity coefficient of this worker: ");
                workerProductivityCoefficient = scanner.nextDouble();
                scanner.nextLine();
                if (Validator.validateCoefficients(workerProductivityCoefficient)){
                    break;
                }
                else {
                    System.out.println("productivity coefficient should be between 1 and 2");
                }
            }

            Workforce workforce = new Workforce();
            workforce.setComponentName(workforceName);
            workforce.setHourlyRate(hourlyRate);
            workforce.setWorkerProductivityCoefficient(workerProductivityCoefficient);
            components.add(workforce);

            System.out.println("Workforce " + workforceName + " Added Successfully!");
            System.out.println("Do you want to add another Workforce ? (Y/N)");
            String choice = scanner.nextLine().trim();
            if(choice.equalsIgnoreCase("N")){
                projectData.setComponents(components);
                break;
            }
        }
    }

    public void handleShowingOverallCostProcess(){
        System.out.println("-------Calculating Overall Cost-------\n");
        System.out.println("Is There any TVA applied ON THE PROJECT ? (Y/N)");
        String choice = scanner.nextLine().trim();
        if(choice.equalsIgnoreCase("Y")){
            System.out.println("ENTER THE VAT VALUE IN %");
            double VAT = scanner.nextDouble();
            scanner.nextLine();
            for(Component c : projectData.getComponents()){
                c.setVAT(VAT);
            }
        }
        System.out.println("Do you wanna apply any profit margin ?");
        String choice1 = scanner.nextLine().trim();
        if(choice1.equalsIgnoreCase("Y")){
            System.out.println("ENTER THE PROFIT MARGIN IN %");

            double profitMargin = scanner.nextDouble();
            scanner.nextLine();
            projectData.setProfitMargin(profitMargin);
        }
        System.out.println("Calculating Overall Price ....");
        handleFetchingProjectDetails();
    }

    public void handleFetchingProjectDetails(){
        System.out.println("-------Fetching Project Details -------\n");
        System.out.println("Project Name :" + projectData.getProjectName());
        System.out.println("Client :" + projectData.getCustomer().getFullName());
        System.out.println("Address :" + projectData.getCustomer().getAddress());

        System.out.println("-----Costs Details-----");

        System.out.println("1 - Materials");
        List<Material> materials = materialService.filterMaterialsOnly(projectData.getComponents());
        for(Material m : materials){
           System.out.println("- " + m.getComponentName() + " : " + materialService.calculateSpecificMaterialCost(m) + " $ (quantity : " + m.getQuantity() + "price per unit :" + m.getPricePerUnit() + "quality : " + m.getQualityCoefficient() + "transportation Cost : " + m.getTransportationCost()   +"$ )" );
        }
        Double baseMaterialCost = materialService.calculateOverallMaterialCost(materials);
        Double finalMaterialCost;
        if (materials.get(0).getVAT() == null){
            System.out.println("No VAT was applied to this project");

            System.out.println("Overall materials price is : " + baseMaterialCost);
        }
        else {
            System.out.println("A TVA OF " + materials.get(0).getVAT() + "AS APPLIED TO THIS PROJECT :") ;
            System.out.println("Overall Material Price Before VAT : " + baseMaterialCost);
            finalMaterialCost = materialService.calculateFinalPriceWithVAT(baseMaterialCost , materials.get(0).getVAT());
            System.out.println("Overall Material Price After VAT : " + finalMaterialCost);
        }

        System.out.println("2 - WorkForce");
        List<Workforce> workforces  = workforceService.filterWorkForceOnly(projectData.getComponents());


    }




}
