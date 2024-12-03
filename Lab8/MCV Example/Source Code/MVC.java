
//  MVC PATTERN
// MAIN CLASS
public class MVC {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model,view);
        controller.setData("Assalamualaikum , This is MVC Architecture");
        controller.updateView();
    }
}
