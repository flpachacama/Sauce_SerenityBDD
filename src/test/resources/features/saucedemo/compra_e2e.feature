Feature: Flujo E2E de compra en SauceDemo
  Como usuario comprador
  Quiero completar una compra de principio a fin
  Para validar que el checkout funciona correctamente

  Scenario: Compra exitosa con dos productos
    Given el comprador abre el portal de SauceDemo
    When inicia sesion con usuario "standard_user" y clave "secret_sauce"
    And agrega los siguientes productos al carrito:
      | producto                 |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
    And visualiza el carrito de compras
    And completa el checkout con los datos:
      | firstName | lastName | zipCode |
      | Freddy    | Leonel   | 110111  |
    And finaliza la compra
    Then debe ver el mensaje de confirmacion "THANK YOU FOR YOUR ORDER"
