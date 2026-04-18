# n11-bootcamp-project1 — Ödeme Sistemi

n11 bootcamp kapsamında geliştirilen basit bir Java ödeme sistemi simülasyonu. Proje; arayüzler (interface), kapsülleme (encapsulation) ve çok biçimlilik (polymorphism) gibi nesne yönelimli programlama prensiplerini uygulamaktadır.

---

## Proje Yapısı

```
src/
├── Main.java
├── Interfaces/
│   ├── IPayment.java
│   └── IPrintable.java
├── User/
│   └── User.java
├── PaymentSystem/
│   └── Payment.java
├── PaymentMethods/
│   ├── ApplePay.java
│   ├── CreditCard.java
│   └── Paypal.java
└── Services/
    ├── UserService.java
    ├── MenuService.java
    └── ActionHandler.java
```

---

## Mimari ve Tasarım Kararları

### `Interfaces/IPrintable.java`
Yazdırma sorumluluğunu ayrı bir arayüzde tanımlar.


### `Interfaces/IPayment.java`
Ödeme yöntemlerine özgü sözleşmeyi tanımlar, `IPrintable` arayüzünü extend eder.


Bu ayrım **Interface Segregation** ve **Single Responsibility** prensiplerine uygundur. Yazdırma sorumluluğu `IPrintable`'da, ödeme kimliği sorumluluğu `IPayment`'ta ayrı ayrı tanımlanmıştır.

---

### `PaymentMethods/`
Her sınıf `IPayment` arayüzünü implement eder ve farklı bir ödeme yöntemini temsil eder.

| Sınıf | Ödeme Yöntemi |
|-------|--------------|
| `ApplePay.java` | Apple Pay |
| `CreditCard.java` | Kredi Kartı |
| `Paypal.java` | PayPal |

Yeni bir ödeme yöntemi eklemek için yalnızca `IPayment` arayüzünü implement eden yeni bir sınıf oluşturmak yeterlidir. Mevcut kodda hiçbir değişiklik gerekmez. Bu **Open/Closed Principle (OCP)** prensibiyle örtüşmektedir.

---

### `User/User.java`
Sistemdeki kullanıcıyı temsil eder. Yalnızca kullanıcı verilerini ve kullanıcıya ait işlemleri yönetir, ödeme mantığıyla ilgilenmez. Bu **Single Responsibility Principle (SRP)** gereğidir.

---

### `PaymentSystem/Payment.java`
Kullanıcı üzerindeki ödeme işlemlerini yönetir. Kullanıcı verilerini değil, yalnızca ödeme mantığını kapsar. Bu **Single Responsibility Principle (SRP)** gereğidir.

---

### `Services/`
`Main.java`'yı tek bir sorumluluktan ibaret hale getirmek için iş mantığı üç servis sınıfına ayrılmıştır.

| Sınıf | Sorumluluk |
|-------|-----------|
| `UserService.java` | Kullanıcı oluşturma ve başlangıç bakiyesi ayarlama |
| `MenuService.java` | Menü gösterimi ve kullanıcı girdisi okuma |
| `ActionHandler.java` | Menü seçimine göre ilgili işlemi yönlendirme |

Bu ayrım **Single Responsibility Principle (SRP)** gereğidir. `Main.java` yalnızca bağımlılıkları bir araya getirir ve döngüyü başlatır.

---

## Kullanılan OOP Prensipleri

- **Single Responsibility (SRP)** — her sınıfın tek bir sorumluluğu vardır
- **Open/Closed (OCP)** — yeni ödeme yöntemi eklemek için mevcut kodu değiştirmek gerekmez, yalnızca yeni bir sınıf oluşturmak yeterlidir
- **Interface Segregation (ISP)** — yazdırma sorumluluğu `IPrintable`'a, ödeme kimliği `IPayment`'a ayrılmıştır
- **Encapsulation** — tüm alanlar `private`, erişim getter/setter üzerinden sağlanır
- **Polymorphism** — `IPayment` arayüzü sayesinde farklı ödeme yöntemleri aynı şekilde kullanılabilir
- **Dependency Injection** — `Payment` ve `ActionHandler` bağımlılıklarını constructor üzerinden alır