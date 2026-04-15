Feature: Checkout en SauceDemo
  Como usuario autenticado
  Quiero completar checkout segun el estado del carrito
  Para validar compra exitosa y bloqueo por carrito vacio

  @workflow_v2 @checkout
  Scenario Outline: Validacion de checkout
    Given el usuario abre la pagina de SauceDemo
    When inicia sesion con "<username>" y "<password>"
    Then valida "<resultado_login>"

    When agrega "<producto>" al carrito
    Then valida "<resultado_producto>"

    When visualiza el carrito
    Then valida "<resultado_carrito>"

    When completa checkout usando "<checkout_case>"
    Then valida "<resultado_checkout>"

    Examples:
      | username      | password     | producto            | checkout_case  | resultado_login | resultado_producto | resultado_carrito | resultado_checkout |
      | standard_user | secret_sauce | Sauce Labs Backpack | checkout_happy | success         | agregado           | carrito_con_items | compra_exitosa     |
      | standard_user | secret_sauce | producto_fake       | checkout_happy | success         | producto_no_existe | carrito_vacio     | checkout_bloqueado |
