Feature: Seleccion de productos en SauceDemo
  Como usuario autenticado
  Quiero agregar productos validos e invalidos al carrito
  Para cubrir flujos positivos y negativos de inventario

  @workflow_v2 @productos
  Scenario Outline: Gestion de productos desde inventario
    Given el usuario abre la pagina de SauceDemo
    When inicia sesion con "<username>" y "<password>"
    Then valida "<resultado_login>"

    When agrega "<producto>" al carrito
    Then valida "<resultado_producto>"

    Examples:
      | username      | password     | producto            | resultado_login | resultado_producto |
      | standard_user | secret_sauce | Sauce Labs Backpack | success         | agregado           |
      | standard_user | secret_sauce | producto_fake       | success         | producto_no_existe |
