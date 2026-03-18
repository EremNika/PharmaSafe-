PharmaSafe — Система учёта лекарственных средств
Цель курса: Разработать систему управления для сети аптек с учётом специфики фармацевтической отрасли: контроль сроков годности, серийных номеров, температурных режимов и рецептурного контроля.

Занятие 1: Модели данных
Теоретическая база: Создание доменных моделей для фармацевтической системы с учётом нормативных требований.
Практическое задание:
Реализовать базовые сущности системы:

Класс	Поля	Методы
Medication (Лекарственный препарат)	id, name, inn (МНН), dosage, form, isPrescription, isNarcotic, isPsychotropic, temperatureMode, shelfLifeDays	Конструктор, геттеры, toString()
TemperatureMode (enum)	ROOM_TEMP (+15...+25), COOL (+8...+15), REFRIGERATOR (+2...+8), FROZEN (-15...-25), MINUS_50 (-50...-30)	—
PharmacyStorage (Помещение аптеки)	id, name, type, supportedMode, currentTemperature, minTemp, maxTemp, capacity	Конструктор, canStore(Medication), checkTemperature(), setCurrentTemperature(), isTemperatureViolation(), геттеры
StorageType (enum)	TRADE_HALL, REFRIGERATOR_UNIT, FREEZER, SAFE, WAREHOUSE	—
Distributor (Фармацевтический дистрибьютор)	id, companyName, licenseNumber, contact, email, hasColdChain, suppliedModes	Конструктор, геттеры, toString()
Домашнее задание:
Дополнить систему моделями для управления партиями и отпуском:
1.	BatchRecord — партия препарата:
•	Поля: batchId, medication, serialNumber, batchNumber, quantity, remaining, manufacturingDate, expiryDate, storage, openedDate, isBlocked
•	Методы: isExpired(), isOpenedExpired(), getDaysUntilExpiry(), openPackage(), block(String reason)
2.	PrescriptionOrder — заказ на отпуск:
•	Поля: orderId, status, createdAt, items, customerName, prescriptionNumber, doctorName, prescriptionDate, pharmacistName, checkerName
•	Методы: addItem(), requiresPrescription(), requiresDoubleCheck(), canChangeStatus(), changeStatus(), assignPharmacist(), assignChecker()
3.	DispenseLine — позиция отпуска:
•	Поля: medication, quantity, priceAtMoment, batchNumber
4.	PrescriptionStatus (enum): CREATED, VERIFIED, ASSEMBLED, CHECKED, ISSUED, REJECTED
5.	В классе Main: создать 2 препарата (1 рецептурный, 1 безрецептурный), 2 помещения и протестировать canStore

Занятие 2: Работа с коллекциями
Теоретическая база: Использование Map и List для хранения данных в памяти, фильтрация и агрегация с учётом сроков годности.
Практическое задание:
В сервисе InventoryService реализовать методы:

Метод	Описание
acceptBatch(Medication, String serialNumber, String batchNumber, int quantity, LocalDate expiryDate, PharmacyStorage storage)	Добавляет в Map<String, List<BatchRecord>> по препаратам, проверяет canStore
getBatchesByMedication(String medicationId)	Все партии препарата
getBatchesExpiringBefore(LocalDate date)	Фильтрация по expiryDate
getTotalStock(String medicationId)	Сумма remaining по всем партиям
findBatchBySerial(String serialNumber)	Поиск по серийному номеру
Домашнее задание:
1.	Реализовать операции управления партиями:
•	transferBatch(String batchId, String toStorageId) — проверка canStore в новом помещении
•	blockExpiredBatches() — автоматическая блокировка isExpired(), возвращает количество заблокированных
2.	Добавить проверку уникальности serialNumber (для наркотических — обязательно)
3.	Создать консольное меню для ввода препарата и приёмки партии

Занятие 3: Объектно-ориентированное программирование
Теоретическая база: Интерфейсы, абстрактные классы, полиморфизм, температурный контроль.
Практическое задание:

Элемент	Описание
Logger (interface)	void log(String message)
ConsoleLogger	Реализация вывода в консоль
AuditLogger	Реализация для контролируемых препаратов (журналирование в файл)
TemperatureControlled (interface)	double getMinTemp(), getMaxTemp(), getCurrentTemp()
RefrigeratedStorage implements TemperatureControlled	Холодильник (+2...+8)
FrozenStorage implements TemperatureControlled	Морозильник (-15...-25)
BasePrescription (abstract)	Базовый класс: number, date, status, pharmacist, геттеры, changeStatus()
PrescriptionOrder extends BasePrescription	Наследует поля, добавляет checkerName для наркотических
Домашнее задание:
1.	Создать TemperatureMonitor — периодическая проверка isTemperatureViolation()
2.	Реализовать ConsoleMenu с пунктами:
•	1 — Добавить препарат
•	2 — Создать помещение
•	3 — Принять партию

Занятие 4: Исключения, перечисления, даты
Теоретическая база: Обработка ошибок, бизнес-правила в enum, работа с LocalDate.
Практическое задание:

Элемент	Описание
PrescriptionStatus.canTransitionTo(PrescriptionStatus)	Правила: CREATED→VERIFIED→ASSEMBLED→CHECKED→ISSUED; REJECTED из любого статуса до ISSUED
ExpiredMedicationException	extends Exception, хранит batchNumber, expiryDate, daysOverdue
InvalidPrescriptionException	extends Exception — неверный номер, просрочен рецепт (>60 дней), нет подписи врача
TemperatureViolationException	extends Exception, хранит current, min, max температуру
PrescriptionValidator	validatePrescriptionData(String number, String doctor, LocalDate date) — проверка формата номера, дата не в будущем
PrescriptionValidator	validatePrescriptionDate(LocalDate date) — рецепт действителен 60 дней
ExpiryValidator	validateBatchExpiry(BatchRecord) — блокировка за 30 дней до срока
TemperatureValidator	validateStorageConditions(Medication, PharmacyStorage) — проверка canStore
Домашнее задание:
1.	Двойная проверка: для isNarcotic или isPsychotropic требуется checkerName в статусе CHECKED
2.	Интегрировать валидацию в DispensingService.verifyPrescription() — выброс InvalidPrescriptionException
3.	Заменить String даты на LocalDate во всех моделях

Занятие 5: Сервисы и принцип единственной ответственности (SRP)
Теоретическая база: Разделение ответственности между классами, сервисный слой, фармацевтическая специфика.
Практическое задание:
Реализовать сервисы:

Сервис	Методы
InventoryService	acceptBatch() с проверкой температуры, writeOff(String batchId, int quantity, String reason) — списание (брак, просрочка), getStockReport(String medicationId) — остатки по партиям
DispensingService	createPrescriptionOrder(String customerName, String prescriptionNumber, String doctorName, LocalDate prescriptionDate) — рецептурный, createOTCOrder(String customerName) — безрецептурный, addItemToOrder(String orderId, String medicationId, int quantity), verifyPrescription(String orderId, String pharmacistName), assembleOrder(String orderId) — резервирование партий (FEFO — сначала с истекающим сроком), pharmacistCheck(String orderId, String pharmacistName), assignChecker(String orderId, String checkerName), issueMedication(String orderId), rejectPrescription(String orderId, String reason)
ExpiryControlService	getExpiringBatches(int days), blockNearExpiry(int daysBeforeExpiry), generateExpiryReport(Period), checkOpenedExpiry() — проверка срока после вскрытия
Домашнее задание:
1.	FEFO при резервировании: сортировка партий по expiryDate (сначала те, что скоро истекают)
2.	Автоматическая блокировка: метод autoBlockExpired() вызывается при старте системы
3.	Отчёт "Препараты с истекающим сроком (< 30 дней)"

Занятие 6: Жизненный цикл отпуска
Теоретическая база: Состояния объектов, переходы между состояниями, контроль качества.
Практическое задание:

Метод	Описание
DispensingService.issueMedication(String orderId)	Проверка: все позиции собраны, CHECKED пройден, для наркотических — checkerName не null, списание с remaining
DispensingService.rejectPrescription(String orderId, String reason)	Любой статус до ISSUED, освобождение резервов
Проверки в changeStatus	Нельзя собрать (ASSEMBLED) без верификации рецепта для рецептурных
Домашнее задание:
1.	Поле pharmacistName обязательно для контролируемых препаратов
2.	История проверок: List<String> verificationLog в PrescriptionOrder
3.	Уведомление при температурном нарушении: метод alertIfViolation(String storageId)

Занятие 7: SOLID, тестирование, пакеты
Теоретическая база: Принципы SOLID, модульность, unit-тестирование с JUnit 5.
Практическое задание:
1.	Структура пакетов:
pharma/
├── model/        # Сущности (Medication, Batch, Order)
├── service/      # Бизнес-логика (Inventory, Dispensing, Expiry)
├── coldchain/    # Температурный контроль
├── validation/   # Валидаторы
├── report/       # Отчёты
├── logger/       # Логирование
├── exception/    # Исключения
└── ui/           # Консольный интерфейс
2.	JUnit 5 тесты:

Тестовый класс	Методы
DispensingServiceTest	testPrescriptionRequiredForControlledDrug(), testDoubleCheckForNarcotics(), testCannotIssueWithoutVerification()
ExpiryControlServiceTest	testAutoBlockExpired(), testFEFOReservation()
InventoryServiceTest	testTemperatureCheckOnAccept()
Домашнее задание:
1.	Минимум 10 тестов: 4 на Dispensing, 3 на Expiry, 3 на Inventory
2.	Проверить: блокировка просроченных, двойная проверка наркотических, FEFO работает корректно
3.	Использовать @BeforeEach для инициализации сервисов

Занятие 8: Защита проекта
Чек-лист готовности системы:
•	[ ] CRUD операции для Medication, PharmacyStorage, Distributor
•	[ ] Приёмка партий с проверкой температурного режима
•	[ ] FEFO при отпуске (сначала с истекающим сроком)
•	[ ] Блокировка за 30 дней до срока годности
•	[ ] Рецептурный контроль: номер, дата, подпись врача
•	[ ] Двойная проверка для наркотических и психотропных препаратов
•	[ ] 3 отчёта: просроченные препараты, температурные нарушения, ПКУ (контролируемые препараты)
•	[ ] 10+ unit-тестов
•	[ ] README.md с инструкцией по запуску

Итоговая спецификация системы PharmaSafe
Архитектура
┌─────────────────┐     ┌──────────────────────┐     ┌─────────────────┐
│   ConsoleMenu   │────▶│  DispensingService   │────▶│  Inventory      │
│     (UI)        │     │                      │     │    Service      │
└─────────────────┘     └──────────────────────┘     └─────────────────┘
         │                       │                            │
         │                       ▼                            │
         │              ┌──────────────────────┐              │
         └─────────────▶│  ExpiryControlService│◀─────────────┘
                        │                      │
                        └──────────────────────┘
                                 │
                    ┌─────────────────────┐
                    │   ColdChainService  │
                    │  (TemperatureMonitor)│
                    └─────────────────────┘
Бизнес-правила системы
1.	Блокировка просроченных — автоматическая блокировка партий за 30 дней до окончания срока годности
2.	Срок после вскрытия — отдельное отслеживание для каждой партии с момента openPackage()
3.	Рецептурный контроль — рецепт действителен 60 дней, проверка номера и подписи врача
4.	Двойная проверка — наркотические (isNarcotic) и психотропные (isPsychotropic) требуют двух фармацевтов (pharmacistName и checkerName)
5.	Температурный режим — каждое помещение имеет допустимый диапазон, при нарушении — уведомление
6.	Серийный учёт — обязательное указание серийного номера при приёмке наркотических препаратов
7.	FEFO — при отпуске сначала расходуются партии с наиболее ранним сроком годности

Тестовые сценарии для проверки
1.	Успешный отпуск: Создать препарат → Принять партию → Создать рецептурный заказ → Верифицировать → Собрать → Проверить → Отпустить
2.	Просроченный препарат: Попытка отпустить просроченную партию → ExpiredMedicationException с деталями
3.	Неверный рецепт: Рецепт выписан 70 дней назад → InvalidPrescriptionException
4.	Температурное нарушение: Текущая температура в холодильнике +12°C (норма +2...+8) → TemperatureViolationException
5.	Двойная проверка: Попытка отпустить наркотический препарат без checkerName → ошибка валидации
6.	FEFO: Две партии с сроками 01.06.2024 и 01.09.2024 → при отпуске выбирается первая

