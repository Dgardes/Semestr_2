export {};

abstract class BaseNotifier 
{
    constructor(protected readonly name: string) {}

    // Абстрактний метод — нащадки зобов'язані реалізувати
    abstract send(to: string, subject: string, body: string): void;

    // Шаблонний метод — спільна логіка для всіх нащадків
    notify(to: string, subject: string, body: string): void 
    {
        console.log(`[${this.name}] Надсилання сповіщення...`);
        this.send(to, subject, body);
        console.log(`[${this.name}] Сповіщення надіслано`);
    }
}
//клас для емейл розсилки
class EmailNotifier extends BaseNotifier
{
    constructor(private readonly smtpServer: string) 
    {
        super("Email");
    }
    //реалізація абстрактного методу батьківського класу
    send(to: string, subject: string, body: string): void 
    {
        const shortBody = body.substring(0, 50);
        console.log("📧 Email → " + to + ": \"" + subject + 
        "\" | Тіло: " + shortBody + " через " + this.smtpServer);
    }
}

//клас для смс розсилки
class SmsNotifier extends BaseNotifier
{
    constructor(private readonly phonePrefix: string = "+380")
    {
        super("SMS");
    }
    //реалізація абстрактного методу батьківського класу
    send(to: string, subject: string, body: string): void 
    {
        const shortBody = body.substring(0, 160);
        console.log("📱 SMS → " + this.phonePrefix + to + ": \"" + shortBody + "\"");
    }
}