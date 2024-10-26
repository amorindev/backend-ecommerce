package com.fernando.backend_ecommerce.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fernando.backend_ecommerce.category.CategoryModel;
import com.fernando.backend_ecommerce.category.ICategoryRepository;
import com.fernando.backend_ecommerce.product.IProductRepository;
import com.fernando.backend_ecommerce.product.ProductModel;
import com.fernando.backend_ecommerce.productgroup.IProductGroupRepository;
import com.fernando.backend_ecommerce.productgroup.ProductGroupModel;
import com.fernando.backend_ecommerce.productvariationoption.IProductVariationOptionRepository;
import com.fernando.backend_ecommerce.productvariationoption.ProductVariationOptionModel;
import com.fernando.backend_ecommerce.role.IRoleRepository;
import com.fernando.backend_ecommerce.role.RoleModel;
import com.fernando.backend_ecommerce.user.IUserRepository;
import com.fernando.backend_ecommerce.user.UserModel;
import com.fernando.backend_ecommerce.variation.IVariationRepository;
import com.fernando.backend_ecommerce.variation.VariationModel;
import com.fernando.backend_ecommerce.variationoption.IVariationOptionRepository;
import com.fernando.backend_ecommerce.variationoption.VariationOptionModel;

import jakarta.transaction.Transactional;




@Configuration
@Transactional
public class DataInizializer implements CommandLineRunner {

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IProductGroupRepository productGroupRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IVariationRepository variationRepository;

    @Autowired 
    private IVariationOptionRepository variationOptionRepository;

    @Autowired
    private IProductVariationOptionRepository productVariationOptionRepository;

    @Override
    public void run(String... args) throws Exception {

        //INsert sql
        initializeRoles();
        initializeSQL();

        Optional<UserModel> adminUser = userRepository.findByUserEmail(adminEmail);
        if (adminUser.isPresent()) {
            throw new RuntimeException("El usuario administrador ya existe");
        }

        Optional<RoleModel> adminRole = roleRepository.findByRoleName("ADMIN");
        if (adminRole.isEmpty()) {
            throw new RuntimeException("El rol ADMIN no existe, asegúrate de que esté creado.");
        }

        
        UserModel userAdmin = new UserModel();
        userAdmin.setUserName("Admin");
        userAdmin.setUserEmail(adminEmail);
        userAdmin.setUserPassword(adminPassword);
        userAdmin.setUserAddress("Admin address");
        userAdmin.setUserPhone("963333333");
        
        List<RoleModel> roles = new ArrayList<>();
        roles.add(adminRole.get());
        userAdmin.setRoles(roles);



        userRepository.save(userAdmin); 
    }

    private void initializeRoles(){
        if(roleRepository.findByRoleName("ADMIN").isEmpty()){
            RoleModel adminRole = new RoleModel();
            adminRole.setRoleName("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Rol ADMIN creado.");
        }
        if (roleRepository.findByRoleName("CLIENT").isEmpty()) {
            RoleModel userRole = new RoleModel();
            userRole.setRoleName("CLIENT");
            roleRepository.save(userRole);
            System.out.println("Rol USER creado.");
        }
    }

    private void initializeSQL() {
        //crear la categoria
        List<String> categoryNames = List.of("Hombre", "Mujer", "Niños", "Sports Equipment");
        List<CategoryModel> categories = new ArrayList<>();

        for (String categoryName : categoryNames) {
            CategoryModel category = new CategoryModel();
            category.setName(categoryName);
            categoryRepository.save(category);
            categories.add(category);
        }

        //Crear el producto
        ProductGroupModel productGroupModel = new ProductGroupModel();
        productGroupModel.setName("Zapatillas Adidas");
        productGroupModel.setDescription("Zapatillas negras deportivas para hombre");
        productGroupModel.setImage("https://drive.google.com/file/d/1wdmZvdhVZtEBX75UCm-19etqdYy--47D/view?usp=drive_link");
        productGroupModel.setCategory(categories.get(0)); 
        productGroupRepository.save(productGroupModel);

        List<ProductModel> products = List.of(
    new ProductModel(400.0, 50, (short) 10, (short) 5, 
        "https://drive.google.com/file/d/1wdmZvdhVZtEBX75UCm-19etqdYy--47D/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123401", productGroupModel),
    new ProductModel(380.99, 30, (short) 5, (short) 4, 
        "https://drive.google.com/file/d/1Q6jae3ZyyLaNTQUgVftkNayiG54Tkz5c/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123402", productGroupModel),
    new ProductModel(370.99, 200, (short) 0, (short) 3, 
        "https://drive.google.com/file/d/16gP1fitwra38UaoG_LYHXMSuCpcoGW6y/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123403", productGroupModel),
    new ProductModel(400.99, 10, (short) 0, (short) 4, 
        "https://drive.google.com/file/d/1qsQwxco_iMDZLmocb2RQHEflWQeMxwqb/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123404", productGroupModel),
    new ProductModel(370.99, 50, (short) 0, (short) 4, 
        "https://drive.google.com/file/d/1xRWDg-AfOuA1EtOV-WowMyXx43058DuO/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123405", productGroupModel),
    new ProductModel(380.0, 10, (short) 30, (short) 5, 
        "https://drive.google.com/file/d/1vrWI4N8tnlLrSHCV_BQrQ89BEFegHwWW/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123406", productGroupModel),
    new ProductModel(370.99, 28, (short) 10, (short) 4, 
        "https://drive.google.com/file/d/14nO2d39p4sEtM_BL5s_V7yValPEP0K4a/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123407", productGroupModel),
    new ProductModel(400.0, 50, (short) 0, (short) 4, 
        "https://drive.google.com/file/d/1AiICrG3LK5exCNFTv4b_P_vnwLKgFdA3/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123408", productGroupModel),
    new ProductModel(399.99, 65, (short) 0, (short) 4, 
        "https://drive.google.com/file/d/1ulRiQlQnmhf1YWCc0NXimpoBsTZGMH-_/view?usp=drive_link", 
        new Timestamp(System.currentTimeMillis()), "SKU123409", productGroupModel)
);

products.forEach(productRepository::save);


        // Crear variaciones
        List<VariationModel> variations = List.of(
            new VariationModel("Color"),
            new VariationModel("Size")
        );

        variations.forEach(variationRepository::save);

        List<VariationOptionModel> variationOptions = List.of(
        new VariationOptionModel("White", variations.get(0)),
        new VariationOptionModel("Black", variations.get(0)),
        new VariationOptionModel("Golden", variations.get(0)),
        new VariationOptionModel("T28", variations.get(1)),
        new VariationOptionModel("T39", variations.get(1)),
        new VariationOptionModel("T42", variations.get(1))
        );
        
        variationOptions.forEach(variationOptionRepository::save);

        List<Object[]> relations = List.of(
            new Object[]{1L, 1L},  
            new Object[]{1L, 6L},  
            new Object[]{2L, 1L},  
            new Object[]{2L, 5L},  
            new Object[]{3L, 1L},  
            new Object[]{3L, 4L},
            new Object[]{4L, 2L},  
            new Object[]{4L, 6L},  
            new Object[]{5L, 2L},  
            new Object[]{5L, 5L},  
            new Object[]{6L, 2L},  
            new Object[]{6L, 4L},
            new Object[]{7L, 3L},  
            new Object[]{7L, 4L},  
            new Object[]{8L, 3L},  
            new Object[]{8L, 2L},  
            new Object[]{9L, 6L},  
            new Object[]{9L, 5L}
        );

        for (Object[] relation : relations) {
            Long prodId = (Long) relation[0];
            Long varopId = (Long) relation[1];

            ProductModel product = productRepository.findById(prodId)
                                   .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            VariationOptionModel variationOption = variationOptionRepository.findById(varopId)
                                              .orElseThrow(() -> new RuntimeException("Opción de variación no encontrada"));

            ProductVariationOptionModel productVariationOption = new ProductVariationOptionModel();
            productVariationOption.setProduct(product);
            productVariationOption.setVariationOption(variationOption);

            productVariationOptionRepository.save(productVariationOption);
        }
    }

    
}
