package com.fernando.backend_ecommerce.productgroup;

import java.util.List;
import java.sql.Timestamp;


public class ProductGroupResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String category;
    private List<ProductResponse> products;
    private List<OptionResponse> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public List<OptionResponse> getOptions() {
        return options;
    }

    public void setOptions(List<OptionResponse> options) {
        this.options = options;
    }

    public static class ProductResponse {
        private Long id;
        private double price;
        private int stock;
        private short discount;
        private short rating;
        private String image;
        private Timestamp createdAt;
        private String sku;
        private List<VariationOptionResponse> variationOptions;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public short getDiscount() {
            return discount;
        }

        public void setDiscount(short discount) {
            this.discount = discount;
        }



        public short getRating() {
            return rating;
        }



        public void setRating(short rating) {
            this.rating = rating;
        }



        public String getImage() {
            return image;
        }



        public void setImage(String image) {
            this.image = image;
        }



        public Timestamp getCreatedAt() {
            return createdAt;
        }



        public void setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
        }



        public String getSku() {
            return sku;
        }



        public void setSku(String sku) {
            this.sku = sku;
        }



        public List<VariationOptionResponse> getVariationOptions() {
            return variationOptions;
        }



        public void setVariationOptions(List<VariationOptionResponse> variationOptions) {
            this.variationOptions = variationOptions;
        }



        public static class VariationOptionResponse {
            private String optionName;
            private String optionValue;

            public String getOptionName() {
                return optionName;
            }
            public void setOptionName(String optionName) {
                this.optionName = optionName;
            }
            public String getOptionValue() {
                return optionValue;
            }
            public void setOptionValue(String optionValue) {
                this.optionValue = optionValue;
            }
            
        }
    }

    public static class OptionResponse {
        private Long id;
        private String name;
        private List<String> values;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public List<String> getValues() {
            return values;
        }
        public void setValues(List<String> values) {
            this.values = values;
        }

        
    }
}

