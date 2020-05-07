package dev.micah.conversation;

import dev.micah.SkyRanks;
import dev.micah.gui.impl.GuiEditor;
import dev.micah.nick.NickHandler;
import dev.micah.rank.Rank;
import dev.micah.runnable.PermissionCheckRunnable;
import dev.micah.utils.Chat;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

public class ConversationHandler {

    public static void startConversationEditPrefix(Player p, String rankEditing) {
        p.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getInstance());
        Conversation conversation = factory.withFirstPrompt(new PrefixA(rankEditing)).withLocalEcho(false).buildConversation(p);
        conversation.begin();
    }

    public static void startConversationEditSuffix(Player p, String rankEditing) {
        p.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getInstance());
        Conversation conversation = factory.withFirstPrompt(new SuffixA(rankEditing)).withLocalEcho(false).buildConversation(p);
        conversation.begin();
    }

    public static void startConversationAddPermission(Player p, String rankEditing) {
        p.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getInstance());
        Conversation conversation = factory.withFirstPrompt(new PermissionAddA(rankEditing)).withLocalEcho(false).buildConversation(p);
        conversation.begin();
    }

    public static void startConversationRemovePermission(Player p, String rankEditing) {
        p.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getInstance());
        Conversation conversation = factory.withFirstPrompt(new PermissionRemoveA(rankEditing)).withLocalEcho(false).buildConversation(p);
        conversation.begin();
    }

    public static void startConversationSetNick(Player p, String rankNicking) {
        p.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getInstance());
        Conversation conversation = factory.withFirstPrompt(new SetNickA(rankNicking)).withLocalEcho(false).buildConversation(p);
        conversation.begin();
    }

}

class PrefixA extends StringPrompt {
    private String rank;
    public PrefixA(String rankIn) {
        this.rank = rankIn;
    }
    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&cPlease type the new prefix for " + rank + "...");
    }
    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&c" + rank + "'s prefix has been changed to &r" + input));
        Rank.setPrefix(rank, input);
        new GuiEditor((Player)context.getForWhom(), rank, GuiEditor.getPageComingFrom().get(context.getForWhom()));
        return null;
    }
}

class SuffixA extends StringPrompt {
    private String rank;
    public SuffixA(String rankIn) {
        this.rank = rankIn;
    }
    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&cPlease type the new suffix for " + rank + "...");
    }
    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&c" + rank + "'s suffix has been changed to &r" + input));
        Rank.setSuffix(rank, input);
        new GuiEditor((Player)context.getForWhom(), rank, GuiEditor.getPageComingFrom().get(context.getForWhom()));
        return null;
    }
}

class PermissionAddA extends StringPrompt {
    private String rank;
    public PermissionAddA(String rankIn) {
        this.rank = rankIn;
    }
    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&cPlease type the permission you want to add for " + rank + "...");
    }
    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&cAdded permission " + input + " to " + rank));
        Rank.addPermission(rank, input);
        new GuiEditor((Player)context.getForWhom(), rank, GuiEditor.getPageComingFrom().get(context.getForWhom()));
        return null;
    }
}

class PermissionRemoveA extends StringPrompt {
    private String rank;
    public PermissionRemoveA(String rankIn) {
        this.rank = rankIn;
    }
    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&cPlease type the permission you want to remove for " + rank + "...");
    }
    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&cRemoved permission " + input + " from " + rank));
        Rank.removePermission(rank, input);
        PermissionCheckRunnable.waitListToRemove.put(rank, input);
        new GuiEditor((Player)context.getForWhom(), rank, GuiEditor.getPageComingFrom().get(context.getForWhom()));
        return null;
    }
}

class SetNickA extends StringPrompt {

    private String rank;
    public SetNickA(String rankNicking) { this.rank = rankNicking; }

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&cPlease type the name you would like to appear as...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&cYou are now nicked!"));
        NickHandler.nick((Player)context.getForWhom(), input, rank);
        return null;
    }
}
