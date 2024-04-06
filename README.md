# Basket_Splitter
Basket Splitter is a Java library designed to split items in a customer's basket into delivery groups based on predefined delivery options.
It utilizes a configuration file to determine the available delivery methods for each product and optimally assigns products to delivery groups to minimize the number of groups while maximizing the number of products in each group.

## Features
- Efficiently splits items in the basket into delivery groups.
- Automatically selects the best delivery option for each product based on the configuration.
- Handles up to 1000 products and 10 different delivery methods.

## Installation
1. Clone the repository to your local machine.
2. Import the project into your preferred Java IDE.
3. Add the necessary dependencies (Jackson ObjectMapper) to your project.
4. Initialize com.ocado.basket.BasketSplitter with the absolute path to the configuration file.

## Usage
*com.ocado.basket.BasketSplitter splitter = new com.ocado.basket.BasketSplitter("path/to/config.json");
List<String> items = Arrays.asList("Product1", "Product2", "Product3");
Map<String, List<String>> deliveryGroups = splitter.split(items);*

## Configuration File
The configuration file (config.json) contains the available delivery options for each product in JSON format.

## Limitations
- Maximum of 1000 products in the configuration file.
- Maximum of 10 different delivery methods.

### Author
Krzysztof Sikora