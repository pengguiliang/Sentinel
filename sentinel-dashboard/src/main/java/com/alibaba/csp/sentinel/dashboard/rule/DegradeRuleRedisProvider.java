/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.constants.RuleConstants;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.util.RedisUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author penggl3
 */
@Component("degradeRuleRedisProvider")
public class DegradeRuleRedisProvider implements DynamicRuleProvider<List<DegradeRuleEntity>> {

    @Autowired
    private RedisUtil redisConfigUtil;

    @Override
    public List<DegradeRuleEntity> getRules(String appName) throws Exception {
        String rules = redisConfigUtil.getString(RuleConstants.RULE_DEGRADE + appName);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return JSONObject.parseArray(rules,DegradeRuleEntity.class);
    }

}
