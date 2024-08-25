# InvseeConsole

**InvseeConsole**, Minecraft sunucularında, sunucu yöneticilerinin ve operatörlerin oyuncu envanterlerini doğrudan konsoldan görmesine ve yönetmesine olanak tanıyan bir Bukkit/Spigot eklentisidir. Bu eklenti, oyuncuların envanterlerini yönetmeyi kolaylaştırır ve konsol üzerinden etkili bir şekilde müdahale etme imkanı sunar.

## Özellikler

- Konsol üzerinden herhangi bir oyuncunun envanterini görüntüleme.
- Konsol üzerinden oyuncu envanterine eşya ekleme veya çıkarma.
- Sunucuda yönetim yetkilerini artırır ve hızlı müdahale sağlar.
- Basit ve kullanıcı dostu komutlar.

## Gereksinimler

- Minecraft 1.8 veya daha yeni bir sürümü.
- Bukkit/Spigot sunucusu.

## Kurulum

1. Eklentiyi [buradan](https://github.com/HaktanOzkan/InvseeConsole/releases) indirin.
2. İndirilen `.jar` dosyasını sunucunuzun `plugins` klasörüne atın.
3. Sunucunuzu yeniden başlatın.

## Kullanım

### Komutlar

- **/invseeconsole <online_player_name>** - Bu, oyuncunun envanterindeki tüm öğeleri görmenizi sağlar.
- **/invseeconsole <player_name> del/delete <slot_number>** - Bu, oyuncunun envanterinde belirtilen yuvadaki öğeyi silmenizi sağlar.
- **/invseeconsole <player_name> delall/deletall** - Bu, oyuncunun envanterindeki tüm öğeleri silmenize olanak tanır
- **/invseeconsole <player_name> replace <slot_number> <item_material> <amount>** - Bu, oyuncunun envanterinde belirtilen yuvadaki öğeyi seçtiğiniz başka bir öğeyle değiştirmenize olanak tanır.
- **/shulkerview <online_player_name> <inventorySlot>** - EnvanterSlot kısmına envanterin bulunduğu slotu yazın.
- **/shulkerreset <online_player_name> <inventorySlot>** - EnvanterSlot kısmına envanterin bulunduğu slotu yazın.

### Örnek Kullanım

```sh
/invsee Player123
/invsee Player123 del 5
/invsee Player123 replace 5 OAK_LOG 64
