# 🥖 Baguete PET - Java/JavaFX

Um adorável mascote de desktop que aparece na sua tela e oferece sua amizade! Mas cuidado... nem tudo é o que parece.

## 📋 Descrição

Baguete PET é uma aplicação de desktop interativa que:
- ✨ Exibe um mascote amigável que se move pela tela
- 🎯 Começa com um diálogo pedindo sua amizade
- 😱 Surpreende você com um jumpscare após alguns segundos
- 🎵 Reproduz efeitos sonoros do sistema
- 👤 Detecta automaticamente o nome do seu perfil do Google Chrome

## 🛠️ Tecnologias

- **Java 11+**
- **JavaFX** - Para interface gráfica
- **Maven** - Para gerenciamento de dependências

## 📦 Pré-requisitos

- Java Development Kit (JDK) 11 ou superior
- Maven 3.6+
- Arquivos de imagem:
  - `mascote.png` - Imagem do baguete normal (200x200)
  - `susto.png` - Imagem do baguete assustador (350x350)

## 🚀 Compilação e Execução

### Clonar o repositório
```bash
git clone https://github.com/ezequiellucasm01-ship-it/baguete-pet-java.git
cd baguete-pet-java
```

### Compilar com Maven
```bash
mvn clean compile
```

### Executar a aplicação
```bash
mvn javafx:run
```

Ou diretamente com Java:
```bash
mvn package
java -jar target/baguete-pet-1.0-SNAPSHOT.jar
```

## 📁 Estrutura do Projeto

```
baguete-pet-java/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/baguete/
│   │   │       ├── BaguetePET.java
│   │   │       ├── GoogleProfileReader.java
│   │   │       ├── MascoteFlotuante.java
│   │   │       └── JumpscareWindow.java
│   │   └── resources/
│   │       ├── mascote.png
│   │       └── susto.png
│   └── test/
│       └── java/
```

## 🎮 Como Usar

1. Execute a aplicação
2. Uma janela de diálogo aparecerá perguntando se você quer ser amigo do Baguete
3. Clique em "Sim" para aceitar
4. O mascote aparecerá na tela e se moverá horizontalmente
5. Após 15 a 40 segundos, o susto acontecerá! 😱

### Comportamento do Botão "Não"
- Tentar clicar em "Não" faz o botão fugir do seu mouse
- Uma caixa de aviso aparece dizendo: "Essa não é uma resposta aceitável. Nós seremos amigos."

## ⚙️ Recursos Principais

### Detecção de Perfil Google
A aplicação tenta buscar o primeiro nome do seu perfil do Google Chrome:
- **Windows**: `%LOCALAPPDATA%\Google\Chrome\User Data\Default\Preferences`
- **Linux**: `~/.config/google-chrome/Default/Preferences`

Se não encontrar, usa a variável de ambiente `USERNAME` ou `USER`.

### Movimento do Mascote
- O mascote se move horizontalmente pela tela
- Inverte a direção ao atingir as bordas
- Movimento suave com atualização a cada 20ms

### Efeito Jumpscare
- Acionado após tempo aleatório (15-40 segundos)
- Fundo vermelho escuro (#700000)
- Tela grande (500x500) no centro da tela
- Reproduz som do sistema
- Fecha automaticamente após 5 segundos

## 🔊 Sons

A aplicação tenta reproduzir sons do sistema:
- **Windows**: `C:\Windows\Media\chord.wav`
- **Linux**: `/usr/share/sounds/alsa/Rear_Center.wav`

## 📝 Licença

Este projeto é fornecido como está para fins educacionais e de entretenimento.

## 👨‍💻 Autor

Desenvolvido como uma conversão de Python para Java/JavaFX

---

**Divirta-se com o Baguete PET! 🥖**
