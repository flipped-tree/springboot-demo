package com.example.pattern.command.command;

import java.util.Iterator;
import java.util.Stack;

public class MacroCommand implements Command {
    // 命令的集合
    private final Stack commands = new Stack();

    // 执行
    public void execute() {
        Iterator it = commands.iterator();
        while (it.hasNext()) {
            ((Command) it.next()).execute();
        }
    }

    // 添加命令
    public void append(Command cmd) {
        if (cmd != this) {
            commands.push(cmd);
        }
    }

    // 删除最后一条命令
    public void undo() {
        if (!commands.empty()) {
            commands.pop();
        }
    }

    // 删除所有命令
    public void clear() {
        commands.clear();
    }
}
