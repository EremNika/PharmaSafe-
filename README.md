PharmaSafe — Система учёта лекарственных средств
Цель курса: Разработать систему управления для сети аптек с учётом специфики фармацевтической отрасли: контроль сроков годности, серийных номеров, температурных режимов и рецептурного контроля.

Занятие 1: Модели данных
Теоретическая база: Создание доменных моделей для фармацевтической системы с учётом нормативных требований.
Практическое задание:
Реализовать базовые сущности системы:

<img width="859" height="610" alt="image" src="https://github.com/user-attachments/assets/cb31f717-2bed-4419-8abf-9bc4156c5e73" />

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

<img width="867" height="369" alt="image" src="https://github.com/user-attachments/assets/c02e35dd-3a66-4ada-a1bf-c4b273064d64" />

Домашнее задание:
1.	Реализовать операции управления партиями:
•	transferBatch(String batchId, String toStorageId) — проверка canStore в новом помещении
•	blockExpiredBatches() — автоматическая блокировка isExpired(), возвращает количество заблокированных
2.	Добавить проверку уникальности serialNumber (для наркотических — обязательно)
3.	Создать консольное меню для ввода препарата и приёмки партии

Занятие 3: Объектно-ориентированное программирование
Теоретическая база: Интерфейсы, абстрактные классы, полиморфизм, температурный контроль.
Практическое задание:
<img width="855" height="532" alt="image" src="https://github.com/user-attachments/assets/c1cf2d2a-4b66-4ab9-8fae-fe2ab1322ead" />

Домашнее задание:
1.	Создать TemperatureMonitor — периодическая проверка isTemperatureViolation()
2.	Реализовать ConsoleMenu с пунктами:
•	1 — Добавить препарат
•	2 — Создать помещение
•	3 — Принять партию

Занятие 4: Исключения, перечисления, даты
Теоретическая база: Обработка ошибок, бизнес-правила в enum, работа с LocalDate.
Практическое задание:
<img width="870" height="678" alt="image" src="https://github.com/user-attachments/assets/bb3404d2-4d96-4039-93ba-d207f37cf5b9" />

Домашнее задание:
1.	Двойная проверка: для isNarcotic или isPsychotropic требуется checkerName в статусе CHECKED
2.	Интегрировать валидацию в DispensingService.verifyPrescription() — выброс InvalidPrescriptionException
3.	Заменить String даты на LocalDate во всех моделях

Занятие 5: Сервисы и принцип единственной ответственности (SRP)
Теоретическая база: Разделение ответственности между классами, сервисный слой, фармацевтическая специфика.
Практическое задание:
Реализовать сервисы:

<img width="859" height="538" alt="image" src="https://github.com/user-attachments/assets/c16aabe8-1d60-4136-97cf-790a45e3a614" />

Домашнее задание:
1.	FEFO при резервировании: сортировка партий по expiryDate (сначала те, что скоро истекают)
2.	Автоматическая блокировка: метод autoBlockExpired() вызывается при старте системы
3.	Отчёт "Препараты с истекающим сроком (< 30 дней)"

Занятие 6: Жизненный цикл отпуска
Теоретическая база: Состояния объектов, переходы между состояниями, контроль качества.
Практическое задание:
<img width="875" height="318" alt="image" src="https://github.com/user-attachments/assets/b02f5137-fc67-4960-b5ee-678c3ed49c57" />

Домашнее задание:
1.	Поле pharmacistName обязательно для контролируемых препаратов
2.	История проверок: List<String> verificationLog в PrescriptionOrder
3.	Уведомление при температурном нарушении: метод alertIfViolation(String storageId)

Занятие 7: SOLID, тестирование, пакеты
Теоретическая база: Принципы SOLID, модульность, unit-тестирование с JUnit 5.
Практическое задание:
1.	Структура пакетов:
<img width="730" height="216" alt="image" src="https://github.com/user-attachments/assets/e43e94ca-1a34-42bd-a60b-cc1fa0ae6973" />

2.	JUnit 5 тесты:
<img width="893" height="299" alt="image" src="https://github.com/user-attachments/assets/d9b0067a-fcfe-4f67-b777-89bf8cfe3345" />

Домашнее задание:
1.	Минимум 10 тестов: 4 на Dispensing, 3 на Expiry, 3 на Inventory
2.	Проверить: блокировка просроченных, двойная проверка наркотических, FEFO работает корректно
3.	Использовать @BeforeEach для инициализации сервисов

Занятие 8: Защита проекта
Чек-лист готовности системы:
•[ ] CRUD операции для Medication, PharmacyStorage, Distributor
•[ ] Приёмка партий с проверкой температурного режима
•[ ] FEFO при отпуске (сначала с истекающим сроком)
•[ ] Блокировка за 30 дней до срока годности
•[ ] Рецептурный контроль: номер, дата, подпись врача
•[ ] Двойная проверка для наркотических и психотропных препаратов
•[ ] 3 отчёта: просроченные препараты, температурные нарушения, ПКУ (контролируемые препараты)
•[ ] 10+ unit-тестов
•[ ] README.md с инструкцией по запуску

Итоговая спецификация системы PharmaSafe
Архитектура
<img width="500" height="304" alt="image" src="https://github.com/user-attachments/assets/6013cdd2-eefb-4993-b213-016930f22c0c" />

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

