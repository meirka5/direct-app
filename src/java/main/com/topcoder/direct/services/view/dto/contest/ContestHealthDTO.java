/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.direct.services.view.dto.contest;

import com.topcoder.direct.services.view.dto.dashboard.DashboardStatusColor;

import java.io.Serializable;

/**
 * <p>A <code>DTO</code> providing the data for health status for single contest.</p>
 *
 * @author TCSDEVELOPER
 * @version 1.0 (Cockpit Performance Improvement Project Overview and Manage Copilot Posting Assembly 1.0)
 */
public class ContestHealthDTO implements Serializable {

    /**
     * <p>A <code>DependenciesStatus</code> providing the overall status for dependencies for target contest.</p>
     */
    private DependenciesStatus dependenciesStatus;

    /**
     * <p>A <code>ReviewersSignupStatus</code> providing the status for reviewers signup for the project.</p>
     */
    private ReviewersSignupStatus reviewersSignupStatus;

    /**
     * <p>An <code>int</code> providing the number of unanswered forum posts for project.</p>
     */
    private int unansweredForumPostsNumber;

    /**
     * <p>A <code>RegistrationStatus</code> providing the status for registration for project.</p>
     */
    private RegistrationStatus registrationStatus;

    /**
     * <p>A <code>RunningPhaseStatus</code> providing the status of the current phase for project.</p>
     */
    private RunningPhaseStatus currentPhaseStatus;

    /**
     * <p>A <code>DashboardStatusColor</code> presents which color should be used when render current phase status to
     * page.</p>
     */
    private DashboardStatusColor phaseStatusColor;

    /**
     * <p>A <code>DashboardStatusColor</code> presents which color should be used when render registration status to
     * page.</p>
     */
    private DashboardStatusColor regStatusColor;

    /**
     * <p>A <code>DashboardStatusColor</code> presents which color should be used when render forum activity status to
     * page.</p>
     */
    private DashboardStatusColor forumActivityStatusColor;

    /**
     * <p>A <code>DashboardStatusColor</code> presents which color should be used when render reviewers sign up status
     * to page.</p>
     */
    private DashboardStatusColor reviewersSignupStatusColor;

    /**
     * <p>A <code>DashboardStatusColor</code> presents which color should be used when render dependencies status to
     * page.</p>
     */
    private DashboardStatusColor dependenciesStatusColor;

    /**
     * <p>A <code>DashboardStatusColor</code> presents which color should be used when render whole contest status to
     * page.</p>
     */
    private DashboardStatusColor contestStatusColor;

    /**
     * <p>Constructs new <code>ContestHealthDTO</code> instance. This implementation does nothing.</p>
     */
    public ContestHealthDTO() {
    }

    /**
     * <p>Gets the overall status for dependencies for target contest.</p>
     *
     * @return a <code>DependenciesStatus</code> providing the overall status for dependencies for target contest.
     */
    public DependenciesStatus getDependenciesStatus() {
        return this.dependenciesStatus;
    }

    /**
     * <p>Sets the overall status for dependencies for target contest.</p>
     *
     * @param dependenciesStatus a <code>DependenciesStatus</code> providing the overall status for dependencies for
     *                           target contest.
     */
    public void setDependenciesStatus(DependenciesStatus dependenciesStatus) {
        this.dependenciesStatus = dependenciesStatus;
    }

    /**
     * <p>Gets the status for reviewers signup for the project.</p>
     *
     * @return a <code>ReviewersSignupStatus</code> providing the status for reviewers signup for the project.
     */
    public ReviewersSignupStatus getReviewersSignupStatus() {
        return this.reviewersSignupStatus;
    }

    /**
     * <p>Sets the status for reviewers signup for the project.</p>
     *
     * @param reviewersSignupStatus a <code>ReviewersSignupStatus</code> providing the status for reviewers signup for
     *        the project.
     */
    public void setReviewersSignupStatus(ReviewersSignupStatus reviewersSignupStatus) {
        this.reviewersSignupStatus = reviewersSignupStatus;
    }

    /**
     * <p>Gets the number of unanswered forum posts for project.</p>
     *
     * @return an <code>int</code> providing the number of unanswered forum posts for project.
     */
    public int getUnansweredForumPostsNumber() {
        return this.unansweredForumPostsNumber;
    }

    /**
     * <p>Sets the number of unanswered forum posts for project.</p>
     *
     * @param unansweredForumPostsNumber an <code>int</code> providing the number of unanswered forum posts for project.
     */
    public void setUnansweredForumPostsNumber(int unansweredForumPostsNumber) {
        this.unansweredForumPostsNumber = unansweredForumPostsNumber;
    }

    /**
     * <p>Gets the status for registration for project.</p>
     *
     * @return a <code>RegistrationStatus</code> providing the status for registration for project.
     */
    public RegistrationStatus getRegistrationStatus() {
        return this.registrationStatus;
    }

    /**
     * <p>Sets the status for registration for project.</p>
     *
     * @param registrationStatus a <code>RegistrationStatus</code> providing the status for registration for project.
     */
    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    /**
     * <p>Gets the status of the current phase for project.</p>
     *
     * @return a <code>RunningPhaseStatus</code> providing the status of the current phase for project.
     */
    public RunningPhaseStatus getCurrentPhaseStatus() {
        return this.currentPhaseStatus;
    }

    /**
     * <p>Sets the status of the current phase for project.</p>
     *
     * @param currentPhaseStatus a <code>RunningPhaseStatus</code> providing the status of the current phase for
     *                           project.
     */
    public void setCurrentPhaseStatus(RunningPhaseStatus currentPhaseStatus) {
        this.currentPhaseStatus = currentPhaseStatus;
    }

    /**
     * Retrieves the phaseStatusColor field.
     *
     * @return the phaseStatusColor
     * @since 1.0.1
     */
    public DashboardStatusColor getPhaseStatusColor() {
        return phaseStatusColor;
    }

    /**
     * Sets the phaseStatusColor field.
     *
     * @param phaseStatusColor
     *            the phaseStatusColor to set
     * @since 1.0.1
     */
    public void setPhaseStatusColor(DashboardStatusColor phaseStatusColor) {
        this.phaseStatusColor = phaseStatusColor;
    }

    /**
     * Retrieves the regStatusColor field.
     *
     * @return the regStatusColor
     * @since 1.0.1
     */
    public DashboardStatusColor getRegStatusColor() {
        return regStatusColor;
    }

    /**
     * Sets the regStatusColor field.
     *
     * @param regStatusColor
     *            the regStatusColor to set
     * @since 1.0.1
     */
    public void setRegStatusColor(DashboardStatusColor regStatusColor) {
        this.regStatusColor = regStatusColor;
    }

    /**
     * Retrieves the forumActivityStatusColor field.
     *
     * @return the forumActivityStatusColor
     * @since 1.0.1
     */
    public DashboardStatusColor getForumActivityStatusColor() {
        return forumActivityStatusColor;
    }

    /**
     * Sets the forumActivityStatusColor field.
     *
     * @param forumActivityStatusColor
     *            the forumActivityStatusColor to set
     * @since 1.0.1
     */
    public void setForumActivityStatusColor(
            DashboardStatusColor forumActivityStatusColor) {
        this.forumActivityStatusColor = forumActivityStatusColor;
    }

    /**
     * Retrieves the reviewersSignupStatusColor field.
     *
     * @return the reviewersSignupStatusColor
     * @since 1.0.1
     */
    public DashboardStatusColor getReviewersSignupStatusColor() {
        return reviewersSignupStatusColor;
    }

    /**
     * Sets the reviewersSignupStatusColor field.
     *
     * @param reviewersSignupStatusColor
     *            the reviewersSignupStatusColor to set
     * @since 1.0.1
     */
    public void setReviewersSignupStatusColor(
            DashboardStatusColor reviewersSignupStatusColor) {
        this.reviewersSignupStatusColor = reviewersSignupStatusColor;
    }

    /**
     * Retrieves the dependenciesStatusColor field.
     *
     * @return the dependenciesStatusColor
     * @since 1.0.1
     */
    public DashboardStatusColor getDependenciesStatusColor() {
        return dependenciesStatusColor;
    }

    /**
     * Sets the dependenciesStatusColor field.
     *
     * @param dependenciesStatusColor
     *            the dependenciesStatusColor to set
     * @since 1.0.1
     */
    public void setDependenciesStatusColor(
            DashboardStatusColor dependenciesStatusColor) {
        this.dependenciesStatusColor = dependenciesStatusColor;
    }

    /**
     * Retrieves the contestStatusColor field.
     *
     * @return the contestStatusColor
     * @since 1.0.1
     */
    public DashboardStatusColor getContestStatusColor() {
        return contestStatusColor;
    }

    /**
     * Sets the contestStatusColor field.
     *
     * @param contestStatusColor
     *            the contestStatusColor to set
     * @since 1.0.1
     */
    public void setContestStatusColor(DashboardStatusColor contestStatusColor) {
        this.contestStatusColor = contestStatusColor;
    }
}
