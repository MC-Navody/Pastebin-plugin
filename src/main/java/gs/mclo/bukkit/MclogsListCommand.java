package gs.mclo.bukkit;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MclogsListCommand extends SubCommand {

    private final CommandMclogs mclogs;

    public MclogsListCommand(CommandMclogs mclogs) {
        this.mclogs = mclogs;
    }

    @Override
    String getPermission() {
        return "mcn.list";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        mclogs.plugin.adventure().sender(sender).sendMessage(Component.empty()
                .append(generateList("logy", mclogs.listLogs()))
                .appendNewline()
                .appendNewline()
                .append(generateList("pády serveru", mclogs.listCrashReports()))
        );
        return true;
    }

    protected @NotNull Component generateList(String name, String[] entries) {
        if (entries.length == 0) {
            return Component.text("Žádné " + name + " nejsou dostupné.");
        }
        Component list = Component.empty().append(Component
                .text("Dostupné " + name + ":")
                .decorate(TextDecoration.UNDERLINED));
        for (String log : entries) {
            list = list.appendNewline().append(Component
                    .text(log)
                    .clickEvent(ClickEvent.runCommand("/log share " + log))
            );
        }

        return list;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        return new ArrayList<>();
    }
}