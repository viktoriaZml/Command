public class FrogCommands {
  public static FrogCommand jumpRightCommand(final Frog frog, final int steps) {
    return new FrogCommand() {
      @Override
      public boolean execute() {
        return frog.jump(steps);
      }

      @Override
      public boolean cancel() {
        return frog.jump(-steps);
      }
    };
  }

  public static FrogCommand jumpLeftCommand(final Frog frog, final int steps) {
    return new FrogCommand() {
      @Override
      public boolean execute() {
        return frog.jump(-steps);
      }

      @Override
      public boolean cancel() {
        return frog.jump(+steps);
      }
    };
  }
}
