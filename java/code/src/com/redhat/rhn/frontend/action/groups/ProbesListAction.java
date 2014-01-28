/**
 * Copyright (c) 2014 Red Hat, Inc.
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */

package com.redhat.rhn.frontend.action.groups;

import java.util.List;

import com.redhat.rhn.domain.server.ManagedServerGroup;
import com.redhat.rhn.frontend.struts.RequestContext;
import com.redhat.rhn.frontend.struts.RhnAction;
import com.redhat.rhn.frontend.struts.RhnHelper;
import com.redhat.rhn.frontend.taglibs.list.helper.ListHelper;
import com.redhat.rhn.frontend.taglibs.list.helper.Listable;
import com.redhat.rhn.manager.monitoring.MonitoringManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ProbesListAction
 * @version 1.0
 */
public class ProbesListAction extends RhnAction implements Listable {

    /** {@inheritDoc} */
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm formIn,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        RequestContext requestContext = new RequestContext(request);
        ManagedServerGroup serverGroup = requestContext.lookupAndBindServerGroup();
        request.setAttribute(RequestContext.SERVER_GROUP_ID, serverGroup.getId());

        ListHelper helper = new ListHelper(this, request);
        helper.execute();

        return actionMapping.findForward(RhnHelper.DEFAULT_FORWARD);
    }

    public List getResult(RequestContext context) {
        ManagedServerGroup serverGroup = context.lookupAndBindServerGroup();
        return MonitoringManager.getInstance().
            probesForSystemGroup(context.getCurrentUser(), serverGroup, null);
    }
}
