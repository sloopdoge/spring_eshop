# spring_eshop
Web Shop
Technologies:
Spring:
  boot
  web
  security
Thymeleaf
Data JPA + PostgreSQL
Entities:
  Product:
  -id - унікальний номер
  -title - назва
  -price - вартість
  -category - категорія
  -image - фото
  User:
  -id - унікальний номер
  -username - логін(використовуємо email)
  -email - електронна скринька
  -phone - номер телефону
  -fullname - ім'я користувача
  -password - пароль
  -role - роль
  -status (ACTIVE, ARCHIVED) - статус (АКТИВНИЙ, АРХІВОВАНИЙ(ВИДАЛЕНИЙ))
  Role(ENUM):
  -CLIENT - покупець
  -ADMIN - адмін, контролює роботу сайту
  -MANAGER - працівник сайту, обробляє замовлення
  Order:
  -id - унікальний номер
  -created date - дата створення
  -last change date - остання дата зміни
  -address - адреса
  -user - чиє замовлення
  -status (NEW, CANCELED, PAID, CLOSED, RETURNED) - статус (нове, відмінене, оплачене, закрите)
  -details (product, prive, amount, comment) - деталі(ТОВАР, ЦІНА, КІЛЬКІСТЬ, КОМЕНТАРІЙ)
  Category:
  -id - унікальний номер
  -title - назва категорії
  Cart:
  -id - унікальний номер
  -user - чия корзинка
  -details(product, amount) - деталі(товар, кількість)
Features:
  -Автентифікація і регістрація користувачів
  -Захист веб-додатку
  -Перегляд товарів
  -Добавлення товарів у корзину
  -Формування заказу
  -Оплата (PayPal)
  -Фільтр/сортування товарів
  -Керування корзиною
  -Сповіщення по Email
  -Навігація по сайту
  -Добавлення товарів через окрему сторінку(ADMIN, MANAGER)
