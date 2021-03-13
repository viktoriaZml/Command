import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<FrogCommand> commands = new ArrayList<>();
    int curCommand = -1;
    Frog frog = new Frog();
    while (true) {
      System.out.println("Введите команду для лягушки:");
      //считываем ввод и конструируем комманду, если
      //пользователь не хотел выйти
      String action = scanner.nextLine();
      if (action.equals("0"))
        break;
      if (action.equals("<<")) {
        if (curCommand < 0) {
          System.out.println("Нечего отменять!");
        } else {
          System.out.println("Отмена");
          commands.get(curCommand).cancel();
          curCommand--;
        }
      } else if (action.equals(">>")) {
        if (curCommand == commands.size() - 1) {
          System.out.println("Нечего повторять!");
        } else {
          curCommand++;
          commands.get(curCommand).execute();
        }
      } else { //пользователь ввёл новое движение для лягушки
        if (curCommand != commands.size() - 1) {
          //удаляем все команды которые были отменены
          for (int i = commands.size() - 1; i > curCommand; i--)
            commands.remove(i);
        }
        FrogCommand cmd = null;
        if (action.startsWith("+")) {
          int steps = Integer.parseInt(action.substring(1));
          //System.out.println("Направо на " + steps + " шагов");
          cmd = FrogCommands.jumpRightCommand(frog, steps);
        } else if (action.startsWith("-")) {
          int steps = Integer.parseInt(action.substring(1));
          //System.out.println("Налево на " + steps + " шагов");
          cmd = FrogCommands.jumpLeftCommand(frog, steps);
        } else if (action.equals("!!")) {
          if (curCommand < 0) {
            System.out.println("Нечего повторять!");
          } else {
            cmd = commands.get(curCommand);
          }
        } else
          System.out.println("Неизвестная команда!");
        if (cmd != null) {
          if (cmd.execute()) {
            curCommand++;
            commands.add(cmd);
          } else
            System.out.println("Действие не выполнено! Прыжок слишком большой!");
        }
      }
      System.out.println("Лягушка в клетке № " + frog.position);
    }
  }
}
