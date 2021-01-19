package com.example.springboot.chain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author xingce
 */
public abstract class AbstractBusinessServiceManager<Request, Response>
        implements BusinessService<Request, Response>, ServiceManager<BusinessService<Request, Response>> {

    private final List<BusinessService<Request, Response>> children = new ArrayList<>();

    /**
     * 链头元素
     */
    protected BusinessService<Request, Response> child;

    @Override
    public void addChild(BusinessService<Request, Response> child) {
        children.add(child);
        this.refresh();
    }

    private void refresh() {
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        children.sort(Comparator.comparingInt(BusinessService<Request, Response>::getOrder));
        this.child = children.get(0);
        BusinessService<Request, Response> lastChild = this.child;
        for (int i = 1; i < children.size(); i++) {
            BusinessService<Request, Response> item = children.get(i);
            if (Objects.nonNull(item)) {
                lastChild.setChild(item);
                lastChild = item;
            }
        }
    }

    @Override
    public BusinessService<Request, Response> setChild(BusinessService<Request, Response> child) {
        addChild(child);
        return this;
    }
}
