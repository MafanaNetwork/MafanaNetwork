package me.tahacheji.mafana.packets;

import me.tahacheji.mafana.packets.fakePlayer.FakePlayer;
import me.tahacheji.mafana.packets.fakePlayer.TabPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabListTemplate implements TabListUpdater {

    private String header = "";
    private String footer = "";


    private List<TabPlayer> staticTabListSlotOne = new ArrayList<>();
    private List<TabPlayer> updatingTabListSlotOne = new ArrayList<>();

    private List<TabPlayer> staticTabListSlotTwo = new ArrayList<>();
    private List<TabPlayer> updatingTabListSlotTwo = new ArrayList<>();

    private List<TabPlayer> staticTabListSlotThree = new ArrayList<>();
    private List<TabPlayer> updatingTabListSlotThree = new ArrayList<>();

    private List<TabPlayer> staticTabListSlotFour = new ArrayList<>();
    private List<TabPlayer> updatingTabListSlotFour = new ArrayList<>();

    public TabListTemplate() {

    }

    public void setHeader(List<String> stringList) {
        this.header = reduce(stringList);
    }

    public void setFooter(List<String> stringList) {
        this.footer = reduce(stringList);
    }

    public void setHeader(String newHeader) {
        this.header = newHeader;
    }

    public void setFooter(String newFooter) {
        this.footer = newFooter;
    }

    public String getFooter() {
        return footer;
    }

    public String getHeader() {
        return header;
    }

    public String appendFooter(String toAppend) {
        this.footer += toAppend;
        return this.footer;
    }


    public String appendHeader(String toAppend) {
        this.header += toAppend;
        return this.header;
    }

    public String replaceFooter(String pattern, String value) {
        this.footer = this.footer.replaceAll(pattern, value);
        return this.footer;
    }

    public String replaceHeader(String pattern, String value) {
        this.header = this.header.replaceAll(pattern, value);
        return this.header;
    }

    public void replace(String pattern, String value) {
        replaceFooter(pattern, value);
        replaceHeader(pattern, value);
    }

    private String reduce(List<String> stringList) {
        return stringList.stream().reduce((acum, actual) -> acum + "\n" + actual).orElse("");
    }


    public void sendAllStatics(Player player) {
        for(TabPlayer tabPlayer : getStaticTabListSlotOne()) {
            tabPlayer.getFakePlayerPacket().sendPacketOnce(player);
        }
        for(TabPlayer tabPlayer : getStaticTabListSlotTwo()) {
            tabPlayer.getFakePlayerPacket().sendPacketOnce(player);
        }
        for(TabPlayer tabPlayer : getStaticTabListSlotThree()) {
            tabPlayer.getFakePlayerPacket().sendPacketOnce(player);
        }
        for(TabPlayer tabPlayer : getUpdatingTabListSlotFour()) {
            tabPlayer.getFakePlayerPacket().sendPacketOnce(player);
        }
    }

    public void setStaticTabListSlotOne (TabPlayer... staticTabListSlotOne) {
        this.staticTabListSlotOne = List.of(staticTabListSlotOne);
    }

    public void setUpdatingTabListSlotOne (TabPlayer... updatingTabListSlotOne) {
        this.updatingTabListSlotOne = List.of(updatingTabListSlotOne);
    }

    public void setStaticTabListSlotTwo(TabPlayer... staticTabListSlotTwo) {
        this.staticTabListSlotTwo = List.of(staticTabListSlotTwo);
    }

    public void setUpdatingTabListSlotTwo(TabPlayer... updatingTabListSlotTwo) {
        this.updatingTabListSlotTwo = List.of(updatingTabListSlotTwo);
    }

    public void setStaticTabListSlotThree(TabPlayer... staticTabListSlotThree) {
        this.staticTabListSlotThree = List.of(staticTabListSlotThree);
    }

    public void setUpdatingTabListSlotThree(TabPlayer... updatingTabListSlotThree) {
        this.updatingTabListSlotThree = List.of(updatingTabListSlotThree);
    }

    public void setStaticTabListSlotFour(TabPlayer... staticTabListSlotFour) {
        this.staticTabListSlotFour = List.of(staticTabListSlotFour);
    }

    public void setUpdatingTabListSlotFour(TabPlayer... updatingTabListSlotFour) {
        this.updatingTabListSlotFour = List.of(updatingTabListSlotFour);
    }

    public void setStaticTabListSlotOne(List<TabPlayer> staticTabListSlotOne) {
        this.staticTabListSlotOne = staticTabListSlotOne;
    }

    public void setUpdatingTabListSlotOne(List<TabPlayer> updatingTabListSlotOne) {
        this.updatingTabListSlotOne = updatingTabListSlotOne;
    }

    public void setStaticTabListSlotTwo(List<TabPlayer> staticTabListSlotTwo) {
        this.staticTabListSlotTwo = staticTabListSlotTwo;
    }

    public void setUpdatingTabListSlotTwo(List<TabPlayer> updatingTabListSlotTwo) {
        this.updatingTabListSlotTwo = updatingTabListSlotTwo;
    }

    public void setStaticTabListSlotThree(List<TabPlayer> staticTabListSlotThree) {
        this.staticTabListSlotThree = staticTabListSlotThree;
    }

    public void setUpdatingTabListSlotThree(List<TabPlayer> updatingTabListSlotThree) {
        this.updatingTabListSlotThree = updatingTabListSlotThree;
    }

    public void setStaticTabListSlotFour(List<TabPlayer> staticTabListSlotFour) {
        this.staticTabListSlotFour = staticTabListSlotFour;
    }

    public void setUpdatingTabListSlotFour(List<TabPlayer> updatingTabListSlotFour) {
        this.updatingTabListSlotFour = updatingTabListSlotFour;
    }

    public void addToStaticTabListSlotOne(TabPlayer tabPlayer) {
        getStaticTabListSlotOne().add(tabPlayer);
    }
    public void addToStaticTabListSlotTwo(TabPlayer tabPlayer) {
        getStaticTabListSlotTwo().add(tabPlayer);
    }
    public void addToStaticTabListSlotThree(TabPlayer tabPlayer) {
        getStaticTabListSlotThree().add(tabPlayer);
    }
    public void addToStaticTabListSlotFour(TabPlayer tabPlayer) {
        getStaticTabListSlotFour().add(tabPlayer);
    }

    public void addToUpdatingTabListSlotOne(TabPlayer tabPlayer) {
        getUpdatingTabListSlotOne().add(tabPlayer);
    }
    public void addToUpdatingTabListSlotTwo(TabPlayer tabPlayer) {
        getUpdatingTabListSlotTwo().add(tabPlayer);
    }
    public void addToUpdatingTabListSlotThree(TabPlayer tabPlayer) {
        getUpdatingTabListSlotThree().add(tabPlayer);
    }
    public void addToUpdatingTabListSlotFour(TabPlayer tabPlayer) {
        getUpdatingTabListSlotFour().add(tabPlayer);
    }

    public List<TabPlayer> getStaticTabListSlotOne() {
        return staticTabListSlotOne;
    }

    public List<TabPlayer> getUpdatingTabListSlotOne() {
        return updatingTabListSlotOne;
    }

    public List<TabPlayer> getStaticTabListSlotTwo() {
        return staticTabListSlotTwo;
    }

    public List<TabPlayer> getUpdatingTabListSlotTwo() {
        return updatingTabListSlotTwo;
    }

    public List<TabPlayer> getStaticTabListSlotThree() {
        return staticTabListSlotThree;
    }

    public List<TabPlayer> getUpdatingTabListSlotThree() {
        return updatingTabListSlotThree;
    }

    public List<TabPlayer> getStaticTabListSlotFour() {
        return staticTabListSlotFour;
    }

    public List<TabPlayer> getUpdatingTabListSlotFour() {
        return updatingTabListSlotFour;
    }


}
