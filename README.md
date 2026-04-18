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
└── PaymentMethods/
    ├── ApplePay.java
    ├── CreditCard.java
    └── Paypal.java
```

---

## Mimari ve Tasarım Kararları

### `Interfaces/IPrintable.java`
Yazdırma sorumluluğunu ayrı bir arayüzde tanımlar.

```java
public interface IPrintable {
    void printPayment(int value);
}
```

### `Interfaces/IPayment.java`
Ödeme yöntemlerine özgü sözleşmeyi tanımlar, `IPrintable` arayüzünü extend eder.

```java
public interface IPayment extends IPrintable {
    String getPaymentType();
}
```

Bu ayrım **Interface Segregation** ve **Single Responsibility** prensiplerine uygundur. Yazdırma sorumluluğu `IPrintable`'da, ödeme kimliği sorumluluğu `IPayment`'ta ayrı ayrı tanımlanmıştır.

`getPaymentType()` metodu, ödeme yöntemini dinamik olarak tanımlamak ve kullanıcıya anlamlı mesajlar göstermek için eklenmiştir. Bu sayede hardcoded string kullanımından kaçınılmıştır.

---

### `PaymentMethods/`
Her sınıf `IPayment` arayüzünü implement eder ve farklı bir ödeme yöntemini temsil eder.

| Sınıf | Ödeme Yöntemi |
|-------|--------------|
| `ApplePay.java` | Apple Pay |
| `CreditCard.java` | Kredi Kartı |
| `Paypal.java` | PayPal |

Her sınıfta `equals()` metodu override edilmiştir. Bu karar, `ArrayList.contains()` metodunun nesneleri referans yerine türlerine göre karşılaştırabilmesi için alınmıştır. Böylece kullanıcının aynı ödeme yöntemini iki kez eklemesi engellenmiştir.

Yeni bir ödeme yöntemi eklemek için yalnızca `IPayment` arayüzünü implement eden yeni bir sınıf oluşturmak yeterlidir. Mevcut kodda hiçbir değişiklik gerekmez. Bu **Open/Closed Principle (OCP)** prensibiyle örtüşmektedir: sistem genişlemeye açık, değişime kapalıdır.

---

### `User/User.java`
Sistemdeki kullanıcıyı temsil eder. Yalnızca kullanıcı verilerini ve kullanıcıya ait işlemleri yönetir, ödeme mantığıyla ilgilenmez. Bu **Single Responsibility Principle (SRP)** gereğidir.

**Alanlar:**
- `username` — kullanıcı adı
- `balance` — mevcut bakiye (varsayılan: 0)
- `payments` — kullanıcının kayıtlı ödeme yöntemleri listesi

**Tasarım Kararları:**
- `addBalance(int value)` ve `setBalance(int value)` metotları birbirinden ayrılmıştır. `addBalance` bakiyeyi artırır/azaltır, `setBalance` ise doğrudan set eder. Bu ayrım, yanlış kullanımın önüne geçmek için yapılmıştır.
- `getPaymentMethods()` her zaman listeyi döner, asla `null` dönmez. Bu sayede `Payment` sınıfında gereksiz null kontrolü yapılmasına gerek kalmaz.

---

### `PaymentSystem/Payment.java`
Kullanıcı üzerindeki ödeme işlemlerini yönetir. Kullanıcı verilerini değil, yalnızca ödeme mantığını kapsar. Bu **Single Responsibility Principle (SRP)** gereğidir; ödeme işlemleri `User` sınıfına değil, ayrı bir `Payment` sınıfına taşınmıştır.

**Tasarım Kararları:**
- `withdrawFromUser` metodunda kontroller sıralı yapılmıştır: önce ödeme yöntemi varlığı, sonra yöntemin kullanıcıya ait olup olmadığı, en son bakiye kontrolü. Bu sıralama, kullanıcıya en anlamlı hata mesajını göstermek için tercih edilmiştir.
- `user` alanı constructor üzerinden inject edilir. Bu, `NullPointerException` riskini ortadan kaldırır ve bağımlılığı açık hale getirir.

---

### `Main.java`
Kullanıcıdan girdi alarak sistemi simüle eden ana sınıftır. `Scanner` ile interaktif bir menü sunulur ve kullanıcı çıkış yapana kadar döngü devam eder.

Mevcut ödeme yöntemleri dinamik bir liste üzerinden gösterilir. Yeni bir ödeme yöntemi eklemek için yalnızca `availableMethods` listesine ekleme yapmak yeterlidir, başka bir değişiklik gerekmez. Bu da **Open/Closed Principle (OCP)** ile örtüşmektedir.

---

## Kullanılan OOP Prensipleri

- **Single Responsibility (SRP)** — her sınıfın tek bir sorumluluğu vardır: `User` kullanıcı verilerini, `Payment` ödeme mantığını, `PaymentMethods` ödeme yöntemlerini yönetir
- **Open/Closed (OCP)** — yeni ödeme yöntemi eklemek için mevcut kodu değiştirmek gerekmez, yalnızca yeni bir sınıf oluşturmak yeterlidir
- **Interface Segregation (ISP)** — yazdırma sorumluluğu `IPrintable`'a, ödeme kimliği `IPayment`'a ayrılmıştır
- **Encapsulation** — tüm alanlar `private`, erişim getter/setter üzerinden sağlanır
- **Polymorphism** — `IPayment` arayüzü sayesinde farklı ödeme yöntemleri aynı şekilde kullanılabilir
- **Dependency Injection** — `Payment` sınıfı `User` nesnesini constructor üzerinden alır