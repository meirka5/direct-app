/*
 * Copyright (C) 2010 - 2011 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.direct.services.view.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.topcoder.direct.services.view.action.AbstractAction;
import com.topcoder.direct.services.view.action.contest.ContestDetailsAction;
import com.topcoder.direct.services.view.action.dashboard.CalendarAction;
import com.topcoder.direct.services.view.action.dashboard.DashboardSearchAction;
import com.topcoder.direct.services.view.action.project.CreateProjectAction;
import com.topcoder.direct.services.view.action.dashboard.DashboardAction;
import com.topcoder.direct.services.view.action.ForwardAction;
import com.topcoder.direct.services.view.action.LandingPage;
import com.topcoder.direct.services.view.action.LoginAction;
import com.topcoder.direct.services.view.action.TopCoderDirectAction;
import com.topcoder.direct.services.view.action.project.ProjectContestsAction;
import com.topcoder.direct.services.view.action.project.ProjectOverviewAction;
import com.topcoder.direct.services.view.action.project.SetCurrentProjectAction;
import com.topcoder.direct.services.view.action.project.ProjectGamePlanAction;
import com.topcoder.direct.services.view.processor.ProcessorsGroup;
import com.topcoder.direct.services.view.processor.RequestProcessor;
import com.topcoder.direct.services.view.processor.UserProjectsProcessor;
import com.topcoder.direct.services.view.processor.contest.ContestDetailsProcessor;
import com.topcoder.direct.services.view.processor.contest.ContestStatsProcessor;
import com.topcoder.direct.services.view.processor.dashboard.DashboardSearchProcessor;
import com.topcoder.direct.services.view.processor.project.*;
import com.topcoder.direct.services.view.processor.security.LoginProcessor;
import com.topcoder.direct.services.view.processor.stats.CoPilotStatsProcessor;
import com.topcoder.direct.services.view.processor.stats.TopCoderDirectFactsProcessor;

/**
 * <p>An interceptor for action invocation chains which is to be used for pre-processing the calls to actions. This
 * interceptor must be injected into invocation chain for all actions which require their state to be set with the
 * data for displaying or have submitted forms processed.</p>
 *
 * <p>Note that this interceptor must be executed only if any conditions for successful action execution are met.
 * For instance, this may mean that validation of the user input must be performed prior to calling this interceptor and
 * if validation fails then this interceptor must not get called.</p>
 *
 * <p>Version 1.1 (Direct Registrants List assembly) change notes:
 *   <ul>
 *     <li>Removed <code>ContestRegistrantsAction</code> from the interception by this processor.</li>
 *   </ul>
 * </p>
 *
 * <p>Version 1.2 (TC Direct Build And Deploy Scripts assembly) change notes:
 *   <ul>
 *     <li>Makes the login processor configurable, in order to support mockup user authentication.</li>
 *   </ul>
 * </p>
 * <p>Version 1.3 (Cockpit Performance Improvement Project Overview and Manage Copilot Posting) change notes:
 *   <ul>
 *     <li>Change <code>UpcomingActivitiesProcessor</code> to <code>UpcomingProjectActivitiesProcessor.</code>
 *  </li>
 *   </ul>
 * </p>
 *
 * @author isv, Blues
 * @version 1.3
 */
public class ActionPreProcessor implements Interceptor {

    /**
     * Represents the full-qualified class name for login processor.
     *
     * @since 1.2
     */
    private String loginProcessorClassName;

    /**
     * <p>Constructs new <code>ActionPreProcessor</code> instance. This implementation does nothing.</p>
     */
    public ActionPreProcessor() {
    }

    /**
     * <p>Initializes this interceptor. This implementation does nothing.</p>
     */
    public void init() {
    }

    /**
     * <p>Destroys this interceptor. This implementation does nothing.</p>
     */
    public void destroy() {
    }

    /**
     * Gets the full-qualified class name for login processor.
     *
     * @return the full-qualified class name for login processor.
     * @since 1.2
     */
    public String getLoginProcessorClassName() {
        return loginProcessorClassName;
    }

    /**
     * Sets the v class name for login processor.
     *
     * @param loginProcessorClassName the full-qualified class name for login processor.
     * @since 1.2
     */
    public void setLoginProcessorClassName(String loginProcessorClassName) {
        this.loginProcessorClassName = loginProcessorClassName;
    }

    /**
     * <p>Intercepts the action invocation chain.</p>
     *
     * @param actionInvocation an <code>ActionInvocation</code> providing the current context for action invocation.
     * @return a <code>String</code> referencing the next view or action to route request to or <code>null</code> if
     *         request has been already handled and response committed.
     * @throws Exception if an unexpected error occurs while running the interception chain.
     */
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        // Get the action which is executed, map it to appropriate request processor and let
        // the processor (if any) to pre-process request
        TopCoderDirectAction action = (TopCoderDirectAction) actionInvocation.getAction();
        RequestProcessor requestProcessor = getRequestProcessor(action);
        if (requestProcessor != null) {
            requestProcessor.processRequest(action);
        }

        String result = actionInvocation.invokeActionOnly();

        if (AbstractAction.SUCCESS.equals(result)) {
            RequestProcessor requestPostProcessor = getRequestPostProcessor(action);
            if (requestPostProcessor != null) {
                requestPostProcessor.processRequest(action);
            }
        }

        return result;
    }

    /**
     * <p>Gets the request processor for handling the request mapped to specified action.</p>
     *
     * <p> Updates in version 1.3:
     * Replace the usage of UpcomingActivitiesProcessor to UpcomingProjectActivitiesProcessor for ProjectOverviewAction
     * </p>
     *
     * @param action an <code>AbstractAction</code> representing the current action mapped to incoming request which is
     *        to be executed.
     * @return an <code>RequestProcessor</code> representing the processor for the specified action.
     */
    private RequestProcessor getRequestProcessor(TopCoderDirectAction action) {
        // TODO : Implement this method : Map the action to request processor based on some configuration-based logic
        if (action instanceof LoginAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new TopCoderDirectFactsProcessor(),
                                                               new CoPilotStatsProcessor(),
                                                               getLoginProcessor()});
        } else if (action instanceof DashboardAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CoPilotStatsProcessor(),
                                                               new UserProjectsProcessor(),
                                                               new LatestActivitiesProcessor(),
                                                               new UpcomingActivitiesProcessor()});
        } else if (action instanceof CalendarAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CoPilotStatsProcessor(),
                                                               new UserProjectsProcessor(),
                                                               new LatestActivitiesProcessor(),
                                                               new UpcomingActivitiesProcessor()});
        } else if (action instanceof LandingPage) {
            return new ProcessorsGroup(new RequestProcessor[] {new TopCoderDirectFactsProcessor(),
                                                               new CoPilotStatsProcessor()});
        } else if (action instanceof CreateProjectAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new UserProjectsProcessor(),
                                                               new CreateProjectProcessor()});
        } else if (action instanceof DashboardSearchAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new DashboardSearchProcessor(),
                                                               new UserProjectsProcessor()});
        } else if (action instanceof SetCurrentProjectAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new UserProjectsProcessor()});
        } else if (action instanceof ForwardAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new UserProjectsProcessor()});
        } else if (action instanceof ProjectOverviewAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new UpcomingProjectActivitiesProcessor(),
                                                               new LatestProjectActivitiesProcessor(),
                                                               new ProjectStatsProcessor(),
                                                               new UserProjectsProcessor()});
        } else if (action instanceof ProjectContestsAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new ProjectContestsListProcessor(),
                                                               new ProjectStatsProcessor(),
                                                               new UserProjectsProcessor()});
        } else if (action instanceof ContestDetailsAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new ContestDetailsProcessor(),
                                                               new ContestStatsProcessor(),
                                                               new UserProjectsProcessor()});
        } else if (action instanceof ProjectGamePlanAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new ProjectStatsProcessor(),
                                                               });
        }else {
            return null;
        }
    }

    /**
     * <p>Gets the request post-processor for handling the request mapped to specified action.</p>
     *
     * <p>Sub-sequent assemblies must implement this method accordingly.</p>
     *
     * @param action an <code>AbstractAction</code> representing the current action mapped to incoming request which is
     *        to be executed.
     * @return an <code>RequestProcessor</code> representing the processor for the specified action.
     */
    private RequestProcessor getRequestPostProcessor(TopCoderDirectAction action) {
        // TODO : Implement this method : Map the action to request processor based on some configuration-based logic
        if (action instanceof LoginAction) {
            return null;
        } else if (action instanceof DashboardAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof CalendarAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof CreateProjectAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof DashboardSearchAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof SetCurrentProjectAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof ForwardAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof ProjectOverviewAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof ProjectContestsAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else if (action instanceof ContestDetailsAction) {
            return new ProcessorsGroup(new RequestProcessor[] {new CurrentProjectContestsProcessor()});
        } else {
            return null;
        }
    }

    /**
     * Gets the login processor.
     *
     * @return the login processor.
     * @since 1.2
     */
    private RequestProcessor getLoginProcessor() {
        if (loginProcessorClassName != null && loginProcessorClassName.trim().length() != 0) {
            try {
            // try to instantiate the configured login processor
            return (RequestProcessor) Class.forName(loginProcessorClassName).newInstance();
            } catch (ClassNotFoundException e) {
                // ignore
            } catch (InstantiationException e) {
                // ignore
            } catch (IllegalAccessException e) {
                // ignore
            }
        }

        return new LoginProcessor();
    }
}
