# <p align="center">AGENDA APP</p>

## ARQUITECTURA DEL PROYECTO
### 1. Modelo (Model): Se utilizará Room para gestionar la base de datos local y Retrofit para interactuar con la base de datos externa.
-	ClassModel.kt: Define la estructura de los datos (Agenda) que se gestionan en la aplicación.
-	AgendaDao.kt: Proporciona métodos para interactuar con la base de datos local (Room), incluyendo inserciones y consultas.
-	ApiService.kt: Define los métodos para interactuar con la API externa mediante Retrofit, incluyendo las llamadas para obtener y agregar datos.
-	AppDatabase.kt: Configura la base de datos local usando Room.

### 2. Vista (View): Utilizaremos Jetpack Compose para crear las interfaces de usuario.
-	AddAgendaActivity.kt: Proporciona la interfaz de usuario para agregar una nueva entrada en la agenda. Utiliza Jetpack Compose para definir la UI de forma declarativa.
-	ViewAgendasActivity.kt: Proporciona la interfaz de usuario para ver las entradas de la agenda almacenadas. También utiliza Jetpack Compose.

### 3. Controlador (Controller): Gestión de la lógica de la aplicación.
-	AgendaController.kt: Actúa como intermediario entre el Modelo y la Vista. Maneja la lógica de negocio para agregar una nueva entrada en la agenda y notifica a la Vista sobre el resultado de esta operación.

## FLUJOS DE DATOS
### 1. Agregar Agenda:
-	Vista: El usuario interactúa con la UI y presiona "Agregar".
-	Controlador: Captura la acción del usuario, llama a la API externa y maneja la respuesta.
-	Modelo: La API agrega la nueva entrada en la base de datos externa y la respuesta se procesa.

### 2. Consultar Agendas:
-	Vista: Solicita los datos de la base de datos local al iniciarse.
-	Modelo: AgendaDao consulta la base de datos local y devuelve los datos.
-	Vista: Muestra los datos recuperados en la UI. 
