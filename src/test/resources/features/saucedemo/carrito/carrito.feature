Feature: Carrito de compras en SauceDemo
  Como usuario autenticado
  Quiero validar el estado del carrito
  Para verificar casos con y sin productos

  @workflow_v2 @carrito
  Scenario Outline: Validacion de carrito
    Given el usuario abre la pagina de SauceDemo
    When inicia sesion con "<username>" y "<password>"
    Then valida "<resultado_login>"

    When agrega "<producto>" al carrito
    Then valida "<resultado_producto>"

    When visualiza el carrito
    Then valida "<resultado_carrito>"

    Examples:
      | username      | password     | producto            | resultado_login | resultado_producto | resultado_carrito |
      | standard_user | secret_sauce | Sauce Labs Backpack | success         | agregado           | carrito_con_items |
      | standard_user | secret_sauce | producto_fake       | success         | producto_no_existe | carrito_vacio     |
