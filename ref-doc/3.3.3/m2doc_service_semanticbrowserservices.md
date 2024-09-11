---
layout: article-services
title: SemanticBrowserServices
subtitle: 3.3.3
relativePath: ../..
---

<!--
/********************************************************************************
** Copyright (c) 2015, 2024 Obeo.
** All rights reserved. This program and the accompanying materials
** are made available under the terms of the Eclipse Public License v2.0
** which accompanies this distribution, and is available at
** http://www.eclipse.org/legal/epl-v20.html
**
** Contributors:
**    Stephane Begaudeau (Obeo) - initial API and implementation
*********************************************************************************/
-->

# SemanticBrowserServices

Services available for the Capella semantic browser.

* TOC
{:toc}

## org.polarsys.capella.core.data.capellacommon.State.getActiveElementsComputed() : Sequence{EObject}

Returns the Sequence of computed active elements for the given State.

### Parameter

* **value**: the State

### Example

* myState.getActiveElementsComputed()
  * the Sequence of computed active elements for the given State

## org.polarsys.capella.core.data.capellacommon.State.getActiveElements() : Sequence{EObject}

Returns the Sequence of active elements for the given State.

### Parameter

* **value**: the State

### Example

* myState.getActiveElements()
  * the Sequence of active elements for the given State

## org.polarsys.capella.core.data.fa.AbstractFunction.getActiveInModes() : Sequence{org.polarsys.capella.core.data.capellacommon.Mode}

Returns the Sequence of active modes for the given AbstractFunction.

### Parameter

* **value**: the AbstractFunction

### Example

* myAbstractFunction.getActiveInModes()
  * the Sequence of active modes for the given AbstractFunction

## org.polarsys.capella.core.data.fa.FunctionalChain.getActiveInModes() : Sequence{org.polarsys.capella.core.data.capellacommon.Mode}

Returns the Sequence of active modes for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getActiveInModes()
  * the Sequence of active modes for the given FunctionalChain

## org.polarsys.capella.core.data.interaction.AbstractCapability.getActiveInModes() : Sequence{org.polarsys.capella.core.data.capellacommon.Mode}

Returns the Sequence of active modes for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getActiveInModes()
  * the Sequence of active modes for the given AbstractCapability

## org.polarsys.capella.core.data.fa.AbstractFunction.getActiveInStates() : Sequence{org.polarsys.capella.core.data.capellacommon.Mode}

Returns the Sequence of active states for the given AbstractFunction.

### Parameter

* **value**: the AbstractFunction

### Example

* myAbstractFunction.getActiveInStates()
  * the Sequence of active states for the given AbstractFunction

## org.polarsys.capella.core.data.fa.FunctionalChain.getActiveInStates() : Sequence{org.polarsys.capella.core.data.capellacommon.Mode}

Returns the Sequence of active states for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getActiveInStates()
  * the Sequence of active states for the given FunctionalChain

## org.polarsys.capella.core.data.interaction.AbstractCapability.getActiveInStates() : Sequence{org.polarsys.capella.core.data.capellacommon.Mode}

Returns the Sequence of active states for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getActiveInStates()
  * the Sequence of active states for the given AbstractCapability

## org.polarsys.capella.common.data.modellingcore.ModelElement.getAllRelatedDiagrams() : Sequence{org.eclipse.sirius.viewpoint.DRepresentationDescriptor}

Returns the Sequence of all related diagrams for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getAllRelatedDiagrams()
  * the Sequence of all related diagrams for the given ModelElement

## org.polarsys.capella.common.data.modellingcore.ModelElement.getAllRelatedTables() : Sequence{org.eclipse.sirius.viewpoint.DRepresentationDescriptor}

Returns the Sequence of all related tables for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getAllRelatedTables()
  * the Sequence of all related tables for the given ModelElement

## org.polarsys.capella.core.data.cs.PhysicalLink.getAllocatedComponentExchanges() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of allocated component exchanges for the given PhysicalLink.

### Parameter

* **value**: the PhysicalLink

### Example

* myPhysicalLink.getAllocatedComponentExchanges()
  * the Sequence of allocated component exchanges for the given PhysicalLink

## org.polarsys.capella.core.data.cs.PhysicalPath.getAllocatedComponentExchanges() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of allocated component exchanges for the given PhysicalPath.

### Parameter

* **value**: the PhysicalPath

### Example

* myPhysicalPath.getAllocatedComponentExchanges()
  * the Sequence of allocated component exchanges for the given PhysicalPath

## org.polarsys.capella.core.data.cs.PhysicalPort.getAllocatedComponentPorts() : Sequence{org.polarsys.capella.core.data.fa.ComponentPort}

Returns the Sequence of allocated component ports links for the given PhysicalPort.

### Parameter

* **value**: the PhysicalPort

### Example

* myPhysicalPort.getAllocatedComponentPorts()
  * the Sequence of allocated component ports for the given PhysicalPort

## org.polarsys.capella.core.data.cs.PhysicalPort.getAllocatedFunctionPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionPort}

Returns the Sequence of allocated function ports links for the given PhysicalPort.

### Parameter

* **value**: the PhysicalPort

### Example

* myPhysicalPort.getAllocatedFunctionPorts()
  * the Sequence of allocated function ports for the given PhysicalPort

## org.polarsys.capella.core.data.fa.ComponentPort.getAllocatedFunctionPorts() : Sequence{org.polarsys.capella.core.data.information.Port}

Returns the Sequence of realized component ports for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getRealizedComponentPorts()
  * the Sequence of realized component ports for the given ComponentPort

## org.polarsys.capella.core.data.fa.ComponentExchange.getAllocatedFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of allocated functional exchances for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getAllocatedFunctionalExchanges()
  * the Sequence of allocated functional exchances for the given ComponentExchange

## org.polarsys.capella.core.data.oa.CommunicationMean.getAllocatedInteractions() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of allocated interactions for the given CommunicationMean.

### Parameter

* **value**: the CommunicationMean

### Example

* myCommunicationMean.getAllocatedInteractions()
  * the Sequence of allocated interactions for the given CommunicationMean

## org.polarsys.capella.core.data.la.LogicalComponent.getAllocatedLogicalFunctions() : Sequence{org.polarsys.capella.core.data.la.LogicalFunction}

Returns the Sequence of allocated logical functions for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getAllocatedLogicalFunctions()
  * the Sequence of realized system components for the given LogicalFunction

## org.polarsys.capella.core.data.oa.Entity.getAllocatedOperationalActivities() : Sequence{org.polarsys.capella.core.data.oa.OperationalActivity}

Returns the Sequence of allocated operational activities for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getAllocatedOperationalActivities()
  * the Sequence of allocated operational activities for the given Entity

## org.polarsys.capella.core.data.oa.Role.getAllocatedOperationalActivities() : Sequence{org.polarsys.capella.core.data.oa.OperationalActivity}

Returns the Sequence of allocating operational activities for the given Role.

### Parameter

* **value**: the Role

### Example

* myAllocation.getAllocatedOperationalActivities()
  * the Sequence of allocating operational activities for the given Role

## org.polarsys.capella.core.data.pa.PhysicalComponent.getAllocatedPhysicalFunctions() : Sequence{org.polarsys.capella.core.data.pa.PhysicalFunction}

Returns the Sequence of allocated physical functions for the given PhysicalComponent.

### Parameter

* **value**: the PhysicalComponent

### Example

* myPhysicalComponent.getAllocatedPhysicalFunctions()
  * the Sequence of allocated physical functions for the given PhysicalComponent

## org.polarsys.capella.core.data.oa.Entity.getAllocatedRoles() : Sequence{org.polarsys.capella.core.data.oa.Role}

Returns the Sequence of allocated roles for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getAllocatedRoles()
  * the Sequence of allocated roles for the given Entity

## org.polarsys.capella.core.data.ctx.SystemComponent.getAllocatedSystemFunctions() : Sequence{org.polarsys.capella.core.data.ctx.SystemFunction}

Returns the Sequence of allocated system functions for the given SystemComponent.

### Parameter

* **value**: the SystemComponent

### Example

* mySystemComponent.getAllocatedSystemFunctions()
  * the Sequence of allocated system functions for the given SystemComponent

## org.polarsys.capella.core.data.fa.AbstractFunction.getAllocatingActorComponentComputed() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of computed actor components for the given AbstractFunction.

### Parameter

* **value**: the AbstractFunction

### Example

* myAbstractFunction.getAllocatingActorComponentComputed()
  * the Sequence of computed actor components for the given AbstractFunction

## org.polarsys.capella.core.data.oa.OperationalActivity.getAllocatingActorEntityRoleComputed() : Sequence{EObject}

Returns the Sequence of computed allocating actor entitie roles for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getAllocatingActorEntityRoleComputed()
  * the Sequence of computed allocating actor entitie roles for the given OperationalActivity

## org.polarsys.capella.core.data.ctx.SystemFunction.getAllocatingActor() : Sequence{EObject}

Returns the Sequence of allocating actors for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getAllocatingActor()
  * the Sequence of allocating actors for the given SystemFunction

## org.polarsys.capella.core.data.fa.FunctionalExchange.getAllocatingCommunicationMean() : Sequence{org.polarsys.capella.core.data.oa.CommunicationMean}

Returns the Sequence of allocating communication means for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getAllocatingCommunicationMean()
  * the Sequence of allocating communication means for the given FunctionalExchange

## org.polarsys.capella.core.data.fa.FunctionalExchange.getAllocatingComponentExchange() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of allocating component exchanges for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getAllocatingComponentExchange()
  * the Sequence of allocating component exchanges for the given FunctionalExchange

## org.polarsys.capella.core.data.fa.FunctionPort.getAllocatingComponentPorts() : Sequence{org.polarsys.capella.core.data.fa.ComponentPort}

Returns the Sequence of allocating component ports for the given FunctionPort.

### Parameter

* **value**: the FunctionPort

### Example

* myFunctionPort.getAllocatingComponentPorts()
  * the Sequence of allocating component ports for the given FunctionPort

## org.polarsys.capella.core.data.oa.Role.getAllocatingEntities() : Sequence{org.polarsys.capella.core.data.oa.Entity}

Returns the Sequence of allocating entities for the given Role.

### Parameter

* **value**: the Role

### Example

* myAllocation.getAllocatingEntities()
  * the Sequence of entities activities for the given Role

## org.polarsys.capella.core.data.oa.OperationalActivity.getAllocatingEntity() : Sequence{EObject}

Returns the Sequence of allocating entities for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getAllocatingEntity()
  * the Sequence of allocating entities for the given OperationalActivity

## org.polarsys.capella.core.data.information.ExchangeItem.getAllocatingExchanges() : Sequence{Object}

Returns the Sequence of allocating exchanges for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getAllocatingExchanges()
  * the Sequence of allocating exchanges for the given ExchangeItem

## org.polarsys.capella.core.data.information.ExchangeItem.getAllocatingFunctionInputPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionInputPort}

Returns the Sequence of allocating function input ports for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getAllocatingFunctionInputPorts()
  * the Sequence of allocating function input ports for the given ExchangeItem

## org.polarsys.capella.core.data.information.ExchangeItem.getAllocatingFunctionOutputPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionOutputPort}

Returns the Sequence of allocating function output ports for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getAllocatingFunctionOutputPorts()
  * the Sequence of allocating function output ports for the given ExchangeItem

## org.polarsys.capella.core.data.la.LogicalFunction.getAllocatingLogicalActor() : Sequence{EObject}

Returns the Sequence of allocating logical actors for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getAllocatingLogicalActor()
  * the Sequence of allocating logical actors for the given LogicalFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getAllocatingLogicalComponent() : Sequence{EObject}

Returns the Sequence of allocating logical components for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getAllocatingLogicalComponent()
  * the Sequence of allocating logical components for the given LogicalFunction

## org.polarsys.capella.core.data.oa.OperationalActivity.getAllocatingOperationalActor() : Sequence{EObject}

Returns the Sequence of allocating operational actors for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getAllocatingOperationalActor()
  * the Sequence of allocating operational actors for the given OperationalActivity

## org.polarsys.capella.core.data.pa.PhysicalFunction.getAllocatingPhysicalActor() : Sequence{EObject}

Returns the Sequence of allocating physical actors for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getAllocatingPhysicalActor()
  * the Sequence of allocating physical actors for the given PhysicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getAllocatingPhysicalComponent() : Sequence{EObject}

Returns the Sequence of allocating physical components for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getAllocatingPhysicalComponent()
  * the Sequence of allocating physical components for the given PhysicalFunction

## org.polarsys.capella.core.data.fa.ComponentExchange.getAllocatingPhysicalLink() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLink}

Returns the Sequence of allocating physical link for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getAllocatingPhysicalLink()
  * the Sequence of allocating physical link for the given ComponentExchange

## org.polarsys.capella.core.data.fa.ComponentExchange.getAllocatingPhysicalPath() : Sequence{org.polarsys.capella.core.data.cs.PhysicalPath}

Returns the Sequence of allocating physical path for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getAllocatingPhysicalPath()
  * the Sequence of allocating physical path for the given ComponentExchange

## org.polarsys.capella.core.data.fa.ComponentPort.getAllocatingPhysicalPorts() : Sequence{org.polarsys.capella.core.data.cs.PhysicalPort}

Returns the Sequence of allocating physical ports for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getAllocatingPhysicalPorts()
  * the Sequence of allocating physical ports for the given ComponentPort

## org.polarsys.capella.core.data.oa.OperationalActivity.getAllocatingRole() : Sequence{org.polarsys.capella.core.data.oa.Role}

Returns the Sequence of allocating roles for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getAllocatingRole()
  * the Sequence of allocating roles for the given OperationalActivity

## org.polarsys.capella.core.data.ctx.SystemFunction.getAllocatingSystem() : Sequence{EObject}

Returns the Sequence of allocating system for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getAllocatingSystem()
  * the Sequence of allocating system for the given SystemFunction

## org.polarsys.capella.core.data.capellacore.CapellaElement.getAppliedPropertyValueGroups() : Sequence{org.polarsys.capella.core.data.capellacore.PropertyValueGroup}

Returns the Sequence of applied property value groups for the given CapellaElement.

### Parameter

* **value**: the CapellaElement

### Example

* myCapellaElement.getAppliedPropertyValueGroups()
  * the Sequence of applied property value groups for the given CapellaElement

## org.polarsys.capella.core.data.capellacore.CapellaElement.getAppliedPropertyValues() : Sequence{org.polarsys.capella.core.data.capellacore.AbstractPropertyValue}

Returns the Sequence of applied property values for the given CapellaElement.

### Parameter

* **value**: the CapellaElement

### Example

* myCapellaElement.getAppliedPropertyValues()
  * the Sequence of applied property values for the given CapellaElement

## org.eclipse.sirius.viewpoint.description.DAnnotation.getAssociatedDiagram() : Sequence{org.eclipse.sirius.viewpoint.DRepresentationDescriptor}

Returns the Sequence of associated diagram for the given DAnnotation.

### Parameter

* **value**: the DAnnotation

### Example

* myDAnnotation.getAssociatedDiagram()
  * the Sequence of associated diagram for the given DAnnotation

## org.eclipse.sirius.viewpoint.description.DAnnotation.getAssociatedElement() : Sequence{EObject}

Returns the Sequence of associated element for the given DAnnotation.

### Parameter

* **value**: the DAnnotation

### Example

* myDAnnotation.getAssociatedElement()
  * the Sequence of associated element for the given DAnnotation

## org.polarsys.capella.core.data.information.Property.getAssociation() : Sequence{org.polarsys.capella.core.data.information.Association}

Returns the Sequence of associations for the given Property.

### Parameter

* **value**: the Property

### Example

* myProperty.getAssociation()
  * the Sequence of associations for the given Property

## org.polarsys.capella.core.data.fa.AbstractFunction.getBreakdown() : Sequence{org.polarsys.capella.core.data.fa.AbstractFunction}

Returns the Sequence of breakdown for the given AbstractFunction.

### Parameter

* **value**: the AbstractFunction

### Example

* myAbstractFunction.getBreakdown()
  * the Sequence of breakdown for the given AbstractFunction

## org.polarsys.capella.core.data.oa.Entity.getBreakdown() : Sequence{org.polarsys.capella.core.data.oa.Entity}

Returns the Sequence of breakdown for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getBreakdown()
  * the Sequence of breakdown for the given Entity

## org.polarsys.capella.core.data.cs.PhysicalLink.getCategories() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLinkCategory}

Returns the Sequence of categories for the given PhysicalLink.

### Parameter

* **value**: the PhysicalLink

### Example

* myPhysicalLink.getCategories()
  * the Sequence of categories for the given PhysicalLink

## org.polarsys.capella.core.data.fa.ComponentExchange.getCategories() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchangeCategory}

Returns the Sequence of categories for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getCategories()
  * the Sequence of categories for the given ComponentExchange

## org.polarsys.capella.core.data.fa.FunctionalExchange.getCategories() : Sequence{org.polarsys.capella.core.data.fa.ExchangeCategory}

Returns the Sequence of categories for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getCategories()
  * the Sequence of categories for the given FunctionalExchange

## org.polarsys.capella.core.data.cs.Component.getCommunicationLink() : Sequence{org.polarsys.capella.core.data.information.communication.CommunicationLink}

Returns the Sequence of communication links for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getCommunicationLink()
  * the Sequence of communication links for the given Component

## org.polarsys.capella.core.data.information.ExchangeItem.getCommunicationLinks() : Sequence{EObject}

Returns the Sequence of communication links for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getCommunicationLinks()
  * the Sequence of communication links for the given ExchangeItem

## org.polarsys.capella.core.data.cs.Component.getComponentBreakdown() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of component breakdown for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getComponentBreakdown()
  * the Sequence of component breakdown for the given Component

## org.polarsys.capella.core.data.fa.ComponentExchangeCategory.getComponentExchanges() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of component exchanges for the given ComponentExchangeCategory.

### Parameter

* **value**: the ComponentExchangeCategory

### Example

* myComponentExchangeCategory.getComponentExchanges()
  * the Sequence of component exchanges for the given ComponentExchangeCategory

## org.polarsys.capella.core.data.cs.Component.getComponentPorts() : Sequence{org.polarsys.capella.core.data.fa.ComponentPort}

Returns the Sequence of component ports for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getComponentPorts()
  * the Sequence of component ports for the given Component

## org.polarsys.capella.core.data.fa.SequenceLink.getCondition() : Sequence{org.polarsys.capella.core.data.capellacore.Constraint}

Returns the Sequence of condition for the given SequenceLink.

### Parameter

* **value**: the SequenceLink

### Example

* mySequenceLink.getCondition()
  * the Sequence of condition for the given SequenceLink

## org.polarsys.capella.core.data.fa.ComponentExchange.getConnectedComponents() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of connected components for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getConnectedComponents()
  * the Sequence of connected components for the given ComponentExchange

## org.polarsys.capella.core.data.fa.ComponentExchange.getConnectedParts() : Sequence{org.polarsys.capella.core.data.cs.Part}

Returns the Sequence of connected parts for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getConnectedParts()
  * the Sequence of connected parts for the given ComponentExchange

## org.polarsys.capella.core.data.capellacore.Constraint.getConstrainedElements() : Sequence{org.polarsys.capella.common.data.modellingcore.ModelElement}

Returns the Sequence of constrained elements for the given Constraint.

### Parameter

* **value**: the Constraint

### Example

* myConstraint.getConstrainedElements()
  * the Sequence of constrained elements for the given Constraint

## org.polarsys.capella.common.data.modellingcore.ModelElement.getConstrainingElements() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractConstraint}

Returns the Sequence of constraining elements for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getConstrainingElements()
  * the Sequence of constraining elements for the given ModelElement

## org.eclipse.sirius.viewpoint.DRepresentationDescriptor.getContextualElements() : Sequence{EObject}

Returns the Sequence of contextual elements for the given DRepresentationDescriptor.

### Parameter

* **value**: the DRepresentationDescriptor

### Example

* myDRepresentationDescriptor.getContextualElements()
  * the Sequence of contextual elements for the given DRepresentationDescriptor

## org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg.getDependencies() : Sequence{org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg}

Returns the Sequence of dependencies for the given AbstractDependenciesPkg.

### Parameter

* **value**: the AbstractDependenciesPkg

### Example

* myAbstractDependenciesPkg.getDependencies()
  * the Sequence of dependencies for the given AbstractDependenciesPkg

## org.polarsys.capella.core.data.pa.PhysicalComponent.getDeployedPhysicalComponents() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of deployed physical components for the given PhysicalComponent.

### Parameter

* **value**: the PhysicalComponent

### Example

* myPhysicalComponent.getDeployedPhysicalComponents()
  * the Sequence of deployed physical components for the given PhysicalComponent

## org.polarsys.capella.core.data.pa.PhysicalComponent.getDeployingPhysicalComponents() : Sequence{org.polarsys.capella.core.data.pa.PhysicalComponent}

Returns the Sequence of deploying physical components for the given PhysicalComponent.

### Parameter

* **value**: the PhysicalComponent

### Example

* myPhysicalComponent.getDeployingPhysicalComponents()
  * the Sequence of deploying physical components for the given PhysicalComponent

## org.polarsys.capella.core.data.capellacommon.State.getDoActivity() : Sequence{org.polarsys.capella.common.data.behavior.AbstractEvent}

Returns the Sequence of do activity for the given State.

### Parameter

* **value**: the State

### Example

* myState.getDoActivity()
  * the Sequence of do activity for the given State

## org.polarsys.capella.core.data.capellacommon.StateTransition.getEffect() : Sequence{org.polarsys.capella.common.data.behavior.AbstractEvent}

Returns the Sequence of effect for the given StateTransition.

### Parameter

* **value**: the StateTransition

### Example

* myStateTransition.getEffect()
  * the Sequence of effect for the given StateTransition

## org.polarsys.kitalpha.emde.model.Element.getElementOfInterestForDiagram() : Sequence{EObject}

Returns the Sequence of element of interest for diagram for the given Element.

### Parameter

* **value**: the Element

### Example

* myModelElement.getElementOfInterestForDiagram()
  * the Sequence of element of interest for diagram for the given Element

## org.eclipse.sirius.viewpoint.DRepresentationDescriptor.getElementsOfInterest() : Sequence{EObject}

Returns the Sequence of element of interest for the given DRepresentationDescriptor.

### Parameter

* **value**: the DRepresentationDescriptor

### Example

* myDRepresentationDescriptor.getElementsOfInterest()
  * the Sequence of element of interest for the given DRepresentationDescriptor

## org.polarsys.capella.common.data.modellingcore.ModelElement.getExchangeContext() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractConstraint}

Returns the Sequence of exchange context for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getExchangeContext()
  * the Sequence of exchange context for the given ModelElement

## org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink.getExchangeContext() : Sequence{org.polarsys.capella.core.data.capellacore.Constraint}

Returns the Sequence of exchange context for the given FunctionalChainInvolvementLink.

### Parameter

* **value**: the FunctionalChainInvolvementLink

### Example

* myFunctionalChainInvolvementLink.getExchangeContext()
  * the Sequence of exchange context for the given FunctionalChainInvolvementLink

## org.polarsys.capella.core.data.capellacore.Type.getExchangeItemElements() : Sequence{org.polarsys.capella.core.data.information.ExchangeItemElement}

Returns the Sequence of exchange item elements for the given Type.

### Parameter

* **value**: the Type

### Example

* myAllocation.getExchangeItemElements()
  * the Sequence of exchange item elements for the given Type

## org.polarsys.capella.core.data.information.ExchangeItem.getExchangeItemElements() : Sequence{org.polarsys.capella.core.data.information.ExchangeItemElement}

Returns the Sequence of echange item elements for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getExchangeItemElements()
  * the Sequence of echange item elements for the given ExchangeItem

## org.polarsys.capella.core.data.cs.ExchangeItemAllocation.getExchangeItem() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem}

Returns the Sequence of exchange items for the given ExchangeItemAllocation.

### Parameter

* **value**: the ExchangeItemAllocation

### Example

* myExchangeItemAllocation.getExchangeItem()
  * the Sequence of exchange items for the given ExchangeItemAllocation

## org.polarsys.capella.core.data.cs.Interface.getExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of exchange items for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getExchangeItems()
  * the Sequence of exchange items for the given Interface

## org.polarsys.capella.core.data.fa.ComponentExchange.getExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of exchange items for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getExchangeItems()
  * the Sequence of exchange items for the given ComponentExchange

## org.polarsys.capella.core.data.fa.FunctionPort.getExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of exchange items for the given FunctionPort.

### Parameter

* **value**: the FunctionPort

### Example

* myFunctionPort.getExchangeItems()
  * the Sequence of exchange items for the given FunctionPort

## org.polarsys.capella.core.data.fa.FunctionalExchange.getExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of exchange items for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getExchangeItems()
  * the Sequence of exchange items for the given FunctionalExchange

## org.polarsys.capella.core.data.interaction.SequenceMessage.getExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of exchange items for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getExchangeItems()
  * the Sequence of exchange items for the given SequenceMessage

## org.polarsys.capella.core.data.ctx.Mission.getExploitedCapabilities() : Sequence{org.polarsys.capella.core.data.ctx.Capability}

Returns the Sequence of exploited capabilities for the given Mission.

### Parameter

* **value**: the Mission

### Example

* myMission.getExploitedCapabilities()
  * the Sequence of exploited capabilities for the given Mission

## org.polarsys.capella.core.data.ctx.Capability.getExploitingMissions() : Sequence{org.polarsys.capella.core.data.ctx.Mission}

Returns the Sequence of exploiting missions for the given Capability.

### Parameter

* **value**: the Capability

### Example

* myCapability.getExploitingMissions()
  * the Sequence of exploiting missions for the given Capability

## org.polarsys.capella.common.data.modellingcore.ModelElement.getExpression() : Sequence{org.polarsys.capella.common.data.modellingcore.ValueSpecification}

Returns the Sequence of expressions for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getExpression()
  * the Sequence of expression for the given ModelElement

## org.polarsys.capella.core.data.interaction.AbstractCapability.getExtendedCapabilities() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of extended capabilities for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getExtendedCapabilities()
  * the Sequence of extended capabilities for the given AbstractCapability

## org.polarsys.capella.core.data.interaction.AbstractCapability.getExtendingCapabilities() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of extending capabilities for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getExtendingCapabilities()
  * the Sequence of extending capabilities for the given AbstractCapability

## org.polarsys.capella.core.data.interaction.SequenceMessage.getFunctionSource() : Sequence{org.polarsys.capella.core.data.fa.AbstractFunction}

Returns the Sequence of function source for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getFunctionSource()
  * the Sequence of function source for the given SequenceMessage

## org.polarsys.capella.core.data.interaction.SequenceMessage.getFunctionTarget() : Sequence{org.polarsys.capella.core.data.fa.AbstractFunction}

Returns the Sequence of function target for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getFunctionTarget()
  * the Sequence of function target for the given SequenceMessage

## org.polarsys.capella.core.data.fa.AbstractFunction.getFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of functional chains for the given AbstractFunction.

### Parameter

* **value**: the AbstractFunction

### Example

* myAbstractFunction.getFunctionalChains()
  * the Sequence of functional chains for the given AbstractFunction

## org.polarsys.capella.core.data.fa.FunctionalExchange.getFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of functional chains for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getFunctionalChains()
  * the Sequence of functional chains for the given FunctionalExchange

## org.polarsys.capella.core.data.fa.ExchangeCategory.getFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of functional echanges for the given ExchangeCategory.

### Parameter

* **value**: the ExchangeCategory

### Example

* myExchangeCategory.getFunctionalExchanges()
  * the Sequence of functional echanges for the given ExchangeCategory

## org.polarsys.capella.core.data.cs.Component.getGeneralizedComponents() : Sequence{org.polarsys.capella.core.data.capellacore.GeneralizableElement}

Returns the Sequence of generalized components for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getGeneralizedComponents()
  * the Sequence of generalized components for the given Component

## org.polarsys.capella.core.data.capellacore.GeneralizableElement.getGeneralizedElements() : Sequence{org.polarsys.capella.core.data.capellacore.GeneralizableElement}

Returns the Sequence of generalized elements for the given GeneralizableElement.

### Parameter

* **value**: the GeneralizableElement

### Example

* myAllocation.getGeneralizedElements()
  * the Sequence of generalized elements for the given GeneralizableElement

## org.polarsys.capella.core.data.interaction.AbstractCapability.getGeneralizedElements() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of generalized elements for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getGeneralizedElements()
  * the Sequence of generalized elements for the given AbstractCapability

## org.polarsys.capella.core.data.cs.Component.getGeneralizingComponents() : Sequence{org.polarsys.capella.core.data.capellacore.GeneralizableElement}

Returns the Sequence of generalizing components for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getGeneralizingComponents()
  * the Sequence of generalizing components for the given Component

## org.polarsys.capella.core.data.capellacore.GeneralizableElement.getGeneralizingElements() : Sequence{org.polarsys.capella.core.data.capellacore.GeneralizableElement}

Returns the Sequence of generalizing elements for the given GeneralizableElement.

### Parameter

* **value**: the GeneralizableElement

### Example

* myAllocation.getGeneralizingElements()
  * the Sequence of generalizing elements for the given GeneralizableElement

## org.polarsys.capella.core.data.interaction.AbstractCapability.getGeneralizingElements() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of generalizing elements for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getGeneralizingElements()
  * the Sequence of generalizing elements for the given AbstractCapability

## org.polarsys.capella.common.data.modellingcore.ModelElement.getGuard() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractConstraint}

Returns the Sequence of guards for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getGuard()
  * the Sequence of guard for the given ModelElement

## org.polarsys.capella.core.data.cs.Component.getImplementedInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of implemented interfaces for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getImplementedInterfaces()
  * the Sequence of implemented interfaces for the given Component

## org.polarsys.capella.core.data.cs.Interface.getImplementors() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of implementors for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getImplementors()
  * the Sequence of implementors for the given Interface

## org.polarsys.capella.core.data.ctx.SystemFunction.getInFlowPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionInputPort}

Returns the Sequence of in flow ports for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getInFlowPorts()
  * the Sequence of in flow ports for the given SystemFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getInFlowPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionInputPort}

Returns the Sequence of in flow ports for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getInFlowPorts()
  * the Sequence of in flow ports for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getInFlowPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionInputPort}

Returns the Sequence of in flow ports for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getInFlowPorts()
  * the Sequence of in flow ports for the given PhysicalFunction

## org.polarsys.capella.core.data.interaction.AbstractCapability.getIncludedCapabilities() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of included capabilities for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getIncludedCapabilities()
  * the Sequence of included capabilities for the given AbstractCapability

## org.polarsys.capella.core.data.interaction.AbstractCapability.getIncludingCapabilities() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of including capabilities for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getIncludingCapabilities()
  * the Sequence of including capabilities for the given AbstractCapability

## org.polarsys.capella.core.data.oa.Entity.getIncomingCommunicationMean() : Sequence{org.polarsys.capella.core.data.oa.CommunicationMean}

Returns the Sequence of incoming communication means for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getIncomingCommunicationMean()
  * the Sequence of incoming communication means for the given Entity

## org.polarsys.capella.core.data.cs.Component.getIncomingComponentExchanges() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of incoming component exchanges for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getIncomingComponentExchanges()
  * the Sequence of incoming component exchanges for the given Component

## org.polarsys.capella.core.data.fa.ComponentPort.getIncomingComponentExchanges() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of incoming component exchanges for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getIncomingComponentExchanges()
  * the Sequence of incoming component exchanges for the given ComponentPort

## org.polarsys.capella.core.data.cs.Component.getIncomingDelegations() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of incoming delegations for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getIncomingDelegations()
  * the Sequence of incoming delegations for the given Component

## org.polarsys.capella.core.data.fa.ComponentPort.getIncomingDelegations() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of incoming delegations for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getIncomingDelegations()
  * the Sequence of incoming delegations for the given ComponentPort

## org.polarsys.capella.common.data.activity.Pin.getIncomingFunctionalExchanges() : Sequence{Object}

Returns the Sequence of incoming functional exchanges for the given Pin.

### Parameter

* **value**: the Pin

### Example

* myPin.getIncomingFunctionalExchanges()
  * the Sequence of incoming functional exchanges for the given Pin

## org.polarsys.capella.core.data.ctx.SystemFunction.getIncomingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of incoming functional exchanges for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getIncomingFunctionalExchanges()
  * the Sequence of incoming functional exchanges for the given SystemFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getIncomingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of incoming functional exchanges for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getIncomingFunctionalExchanges()
  * the Sequence of incoming functional exchanges for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getIncomingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of incoming functional exchanges for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getIncomingFunctionalExchanges()
  * the Sequence of incoming functional exchanges for the given PhysicalFunction

## org.polarsys.capella.common.data.modellingcore.TraceableElement.getIncomingGenericTraces() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of incoming generic traces for the given TraceableElement.

### Parameter

* **value**: the TraceableElement

### Example

* myTraceableElement.getIncomingGenericTraces()
  * the Sequence of incoming generic traces for the given TraceableElement

## org.polarsys.capella.core.data.oa.OperationalActivity.getIncomingInteractions() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of incoming interactions for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getIncomingInteractions()
  * the Sequence of incoming interactions for the given OperationalActivity

## org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction.getIncomingInvolvementLinks() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvement}

Returns the Sequence of incoming involvement links for the given FunctionalChainInvolvementFunction.

### Parameter

* **value**: the FunctionalChainInvolvementFunction

### Example

* myFunctionalChainInvolvementFunction.getIncomingInvolvementLinks()
  * the Sequence of incoming involvement links for the given FunctionalChainInvolvementFunction

## org.polarsys.capella.common.data.modellingcore.IState.getIncomingTransition() : Sequence{org.polarsys.capella.core.data.capellacommon.StateTransition}

Returns the Sequence of incoming transitions for the given IState.

### Parameter

* **value**: the IState

### Example

* myIState.getIncomingTransition()
  * the Sequence of incoming transitions for the given IState

## org.polarsys.capella.core.data.cs.PhysicalLink.getInheritedCategories() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLinkCategory}

Returns the Sequence of inherited categories for the given PhysicalLink.

### Parameter

* **value**: the PhysicalLink

### Example

* myPhysicalLink.getScenarios()
  * the Sequence of inherited categories for the given PhysicalLink

## org.polarsys.capella.core.data.fa.ComponentExchange.getInheritedCategories() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchangeCategory}

Returns the Sequence of inherited categories for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getInheritedCategories()
  * the Sequence of inherited categories for the given ComponentExchange

## org.polarsys.capella.core.data.cs.Interface.getInheritedExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of inherited exchange items for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getInheritedExchangeItems()
  * the Sequence of inherited exchange items for the given Interface

## org.polarsys.capella.common.data.modellingcore.AbstractType.getInheritedOfTypingElements() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractTypedElement}

Returns the Sequence of inherited of typing elements for the given AbstractType.

### Parameter

* **value**: the AbstractType

### Example

* myAbstractType.getInheritedOfTypingElements()
  * the Sequence of inherited of typing elements for the given AbstractType

## org.polarsys.capella.core.data.information.ExchangeItem.getInterfaces() : Sequence{EObject}

Returns the Sequence of interfaces for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getInterfaces()
  * the Sequence of interfaces for the given ExchangeItem

## org.polarsys.capella.core.data.cs.Component.getInternalIncomingComponentExchangesComputed() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of computed incoming component echanges for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getInternalIncomingComponentExchangesComputed()
  * the Sequence of computed incoming component echanges for the given Component

## org.polarsys.capella.core.data.ctx.SystemFunction.getInternalIncomingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of internal incoming functional exchanges for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getInternalIncomingFunctionalExchanges()
  * the Sequence of internal incoming functional exchanges for the given SystemFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getInternalIncomingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of incoming functional exchanges for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getInternalIncomingFunctionalExchanges()
  * the Sequence of incoming functional exchanges for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getInternalIncomingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of internal incoming functional exchanges for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getInternalIncomingFunctionalExchanges()
  * the Sequence of internal incoming functional exchanges for the given PhysicalFunction

## org.polarsys.capella.core.data.oa.OperationalActivity.getInternalIncomingInteractions() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of internal incoming interaction for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getInternalIncomingInteractions()
  * the Sequence of internal incoming interaction for the given OperationalActivity

## org.polarsys.capella.core.data.cs.Component.getInternalOutgoingComponentExchangesComputed() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of outgoing component exchanges for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getInternalOutgoingComponentExchangesComputed()
  * the Sequence of outgoing component exchanges for the given Component

## org.polarsys.capella.core.data.ctx.SystemFunction.getInternalOutgoingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of internal outgoing functional exchanges for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getInternalOutgoingFunctionalExchanges()
  * the Sequence of internal outgoing functional exchanges for the given SystemFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getInternalOutgoingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of outgoing functional exchanges for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getInternalOutgoingFunctionalExchanges()
  * the Sequence of outgoing functional exchanges for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getInternalOutgoingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of internal outgoing functional exchanges for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getInternalOutgoingFunctionalExchanges()
  * the Sequence of internal outgoing functional exchanges for the given PhysicalFunction

## org.polarsys.capella.core.data.oa.OperationalActivity.getInternalOutgoingInteractions() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of internal outgoing interactions for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getInternalOutgoingInteractions()
  * the Sequence of internal outgoing interactions for the given OperationalActivity

## org.polarsys.capella.core.data.pa.PhysicalComponent.getInternalPhysicalLinksComputed() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLink}

Returns the Sequence of computed internal physical links for the given PhysicalComponent.

### Parameter

* **value**: the PhysicalComponent

### Example

* myPhysicalComponent.getInternalPhysicalLinksComputed()
  * the Sequence of computed internal physical links for the given PhysicalComponent

## org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg.getInverseDependencies() : Sequence{org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg}

Returns the Sequence of inverse dependencies for the given AbstractDependenciesPkg.

### Parameter

* **value**: the AbstractDependenciesPkg

### Example

* myAbstractDependenciesPkg.getInverseDependencies()
  * the Sequence of inverse dependencies for the given AbstractDependenciesPkg

## org.polarsys.capella.core.data.interaction.SequenceMessage.getInvokedCommunicationMean() : Sequence{org.polarsys.capella.core.data.information.AbstractEventOperation}

Returns the Sequence of invoked communication mean for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getInvokedCommunicationMean()
  * the Sequence of invoked communication mean for the given SequenceMessage

## org.polarsys.capella.core.data.interaction.SequenceMessage.getInvokedComponentExchange() : Sequence{org.polarsys.capella.core.data.information.AbstractEventOperation}

Returns the Sequence of invoked component exchange for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getInvokedComponentExchange()
  * the Sequence of invoked component exchange for the given SequenceMessage

## org.polarsys.capella.core.data.interaction.SequenceMessage.getInvokedExchangeItemAllocation() : Sequence{org.polarsys.capella.core.data.information.AbstractEventOperation}

Returns the Sequence of invoked exchange item for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getInvokedExchangeItemAllocation()
  * the Sequence of invoked exchange item for the given SequenceMessage

## org.polarsys.capella.core.data.interaction.SequenceMessage.getInvokedFunctionalExchange() : Sequence{org.polarsys.capella.core.data.information.AbstractEventOperation}

Returns the Sequence of invoked functional exchange for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getInvokedFunctionalExchange()
  * the Sequence of invoked functional exchange for the given SequenceMessage

## org.polarsys.capella.core.data.interaction.SequenceMessage.getInvokedInteraction() : Sequence{org.polarsys.capella.core.data.information.AbstractEventOperation}

Returns the Sequence of invoked interaction for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getInvokedInteraction()
  * the Sequence of invoked interaction for the given SequenceMessage

## org.polarsys.capella.core.data.interaction.SequenceMessage.getInvokedOperation() : Sequence{org.polarsys.capella.core.data.information.AbstractEventOperation}

Returns the Sequence of invoked operation for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getInvokedOperation()
  * the Sequence of invoked operation for the given SequenceMessage

## org.polarsys.capella.core.data.fa.FunctionalChain.getInvolvedComponents() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of involved components for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getInvolvedComponents()
  * the Sequence of involved components for the given FunctionalChain

## org.polarsys.capella.core.data.interaction.AbstractCapability.getInvolvedComponents() : Sequence{org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement}

Returns the Sequence of involved components for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getInvolvedComponents()
  * the Sequence of involved components for the given AbstractCapability

## org.polarsys.capella.core.data.capellacore.Involvement.getInvolvedElement() : Sequence{org.polarsys.capella.core.data.capellacore.InvolvedElement}

Returns the Sequence of involved elements for the given Involvement.

### Parameter

* **value**: the Involvement

### Example

* myInvolvement.getInvolvedElement()
  * the Sequence of involved elements for the given Involvement

## org.polarsys.capella.core.data.ctx.CapabilityExploitation.getInvolvedElement() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of involved elements for the given CapabilityExploitation.

### Parameter

* **value**: the CapabilityExploitation

### Example

* myCapabilityExploitation.getInvolvedElement()
  * the Sequence of involved elements for the given CapabilityExploitation

## org.polarsys.capella.core.data.oa.OperationalCapability.getInvolvedEntities() : Sequence{org.polarsys.capella.core.data.oa.Entity}

Returns the Sequence of involved entities for the given OperationalCapability.

### Parameter

* **value**: the OperationalCapability

### Example

* myOperationalCapability.getInvolvedEntities()
  * the Sequence of involved entities for the given OperationalCapability

## org.polarsys.capella.core.data.ctx.Capability.getInvolvedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of involved functional chains for the given Capability.

### Parameter

* **value**: the Capability

### Example

* myCapability.getInvolvedFunctionalChains()
  * the Sequence of involved functional chains for the given Capability

## org.polarsys.capella.core.data.fa.FunctionalChain.getInvolvedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainReference}

Returns the Sequence of involved functional chains for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getInvolvedFunctionalChains()
  * the Sequence of involved functional chains for the given FunctionalChain

## org.polarsys.capella.core.data.fa.FunctionalChainReference.getInvolvedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainReference}

Returns the Sequence of involved functional chains for the given FunctionalChainReference.

### Parameter

* **value**: the FunctionalChainReference

### Example

* myFunctionalChainReference.getInvolvedFunctionalChains()
  * the Sequence of involved functional chains for the given FunctionalChainReference

## org.polarsys.capella.core.data.la.CapabilityRealization.getInvolvedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of involved functional chains for the given CapabilityRealization.

### Parameter

* **value**: the CapabilityRealization

### Example

* myCapabilityRealization.getInvolvedFunctionalChains()
  * the Sequence of involved functional chains for the given CapabilityRealization

## org.polarsys.capella.core.data.fa.FunctionalChain.getInvolvedFunctions() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction}

Returns the Sequence of involved functions for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getInvolvedFunctions()
  * the Sequence of involved functions for the given FunctionalChain

## org.polarsys.capella.core.data.fa.FunctionalChainReference.getInvolvedFunctions() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction}

Returns the Sequence of involved functions for the given FunctionalChainReference.

### Parameter

* **value**: the FunctionalChainReference

### Example

* myFunctionalChainReference.getInvolvedFunctions()
  * the Sequence of involved functions for the given FunctionalChainReference

## org.polarsys.capella.core.data.la.CapabilityRealization.getInvolvedLogicalFunctions() : Sequence{org.polarsys.capella.core.data.la.LogicalFunction}

Returns the Sequence of involved logical functions for the given CapabilityRealization.

### Parameter

* **value**: the CapabilityRealization

### Example

* myCapabilityRealization.getInvolvedLogicalFunctions()
  * the Sequence of involved logical functions for the given CapabilityRealization

## org.polarsys.capella.core.data.oa.OperationalProcess.getInvolvedOperationalActivities() : Sequence{org.polarsys.capella.core.data.oa.OperationalActivity}

Returns the Sequence of involved operational activities for the given OperationalProcess.

### Parameter

* **value**: the OperationalProcess

### Example

* myOperationalProcess.getInvolvedOperationalActivities()
  * the Sequence of involved operational activities for the given OperationalProcess

## org.polarsys.capella.core.data.oa.OperationalCapability.getInvolvedOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of involved operational process for the given OperationalCapability.

### Parameter

* **value**: the OperationalCapability

### Example

* myOperationalCapability.getInvolvedOperationalProcesses()
  * the Sequence of involved operational process for the given OperationalCapability

## org.polarsys.capella.core.data.oa.OperationalProcess.getInvolvedOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of involved operational processes for the given OperationalProcess.

### Parameter

* **value**: the OperationalProcess

### Example

* myOperationalProcess.getInvolvedOperationalProcesses()
  * the Sequence of involved operational processes for the given OperationalProcess

## org.polarsys.capella.core.data.cs.PhysicalPathInvolvement.getInvolvedPhysicalComponent() : Sequence{org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement}

Returns the Sequence of involved physical components for the given PhysicalPathInvolvement.

### Parameter

* **value**: the PhysicalPathInvolvement

### Example

* myPhysicalPathInvolvement.getInvolvedPhysicalComponent()
  * the Sequence of involved physical components for the given PhysicalPathInvolvement

## org.polarsys.capella.core.data.la.CapabilityRealization.getInvolvedPhysicalFunctions() : Sequence{org.polarsys.capella.core.data.pa.PhysicalFunction}

Returns the Sequence of involved physical functions for the given CapabilityRealization.

### Parameter

* **value**: the CapabilityRealization

### Example

* myCapabilityRealization.getInvolvedPhysicalFunctions()
  * the Sequence of involved physical functions for the given CapabilityRealization

## org.polarsys.capella.core.data.cs.PhysicalPathInvolvement.getInvolvedPhysicalLink() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLink}

Returns the Sequence of involved physical links for the given PhysicalPathInvolvement.

### Parameter

* **value**: the PhysicalPathInvolvement

### Example

* myPhysicalPathInvolvement.getInvolvedPhysicalLink()
  * the Sequence of involved physical links for the given PhysicalPathInvolvement

## org.polarsys.capella.core.data.cs.PhysicalPath.getInvolvedPhysicalLinks() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLink}

Returns the Sequence of involved physical links for the given PhysicalPath.

### Parameter

* **value**: the PhysicalPath

### Example

* myPhysicalPath.getInvolvedPhysicalLinks()
  * the Sequence of involved physical links for the given PhysicalPath

## org.polarsys.capella.core.data.cs.PhysicalPathInvolvement.getInvolvedPhysicalPath() : Sequence{org.polarsys.capella.core.data.cs.PhysicalPath}

Returns the Sequence of involved physical paths for the given PhysicalPathInvolvement.

### Parameter

* **value**: the PhysicalPathInvolvement

### Example

* myPhysicalPathInvolvement.getInvolvedPhysicalPath()
  * the Sequence of involved physical paths for the given PhysicalPathInvolvement

## org.polarsys.capella.core.data.cs.PhysicalPath.getInvolvedPhysicalPaths() : Sequence{org.polarsys.capella.core.data.cs.PhysicalPath}

Returns the Sequence of involved physical paths for the given PhysicalPath.

### Parameter

* **value**: the PhysicalPath

### Example

* myPhysicalPath.getInvolvedPhysicalPaths()
  * the Sequence of involved physical paths for the given PhysicalPath

## org.polarsys.capella.common.data.modellingcore.IState.getInvolvedStatesAndModes() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of involved state and mode for the given IState.

### Parameter

* **value**: the IState

### Example

* myIState.getInvolvedStatesAndModes()
  * the Sequence of involved state and mode for the given IState

## org.polarsys.capella.core.data.ctx.Mission.getInvolvedSystemComponents() : Sequence{org.polarsys.capella.core.data.ctx.SystemComponent}

Returns the Sequence of involved system components for the given Mission.

### Parameter

* **value**: the Mission

### Example

* myMission.getInvolvedSystemComponents()
  * the Sequence of involved system components for the given Mission

## org.polarsys.capella.core.data.ctx.Capability.getInvolvedSystemFunctions() : Sequence{org.polarsys.capella.core.data.ctx.SystemFunction}

Returns the Sequence of involved system functions for the given Capability.

### Parameter

* **value**: the Capability

### Example

* myCapability.getInvolvedSystemFunctions()
  * the Sequence of involved system functions for the given Capability

## org.polarsys.capella.core.data.fa.FunctionalChain.getInvolvementLinks() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink}

Returns the Sequence of involvement links for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getInvolvementLinks()
  * the Sequence of involvement links for the given FunctionalChain

## org.polarsys.capella.core.data.fa.FunctionalChainReference.getInvolvementLinks() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink}

Returns the Sequence of involved links for the given FunctionalChainReference.

### Parameter

* **value**: the FunctionalChainReference

### Example

* myFunctionalChainReference.getInvolvementLinks()
  * the Sequence of involved links for the given FunctionalChainReference

## org.polarsys.capella.core.data.ctx.SystemComponent.getInvolvingCapabilities() : Sequence{org.polarsys.capella.core.data.ctx.Capability}

Returns the Sequence of involving capabilities for the given SystemComponent.

### Parameter

* **value**: the SystemComponent

### Example

* mySystemComponent.getInvolvingCapabilities()
  * the Sequence of involving capabilities for the given SystemComponent

## org.polarsys.capella.core.data.ctx.SystemFunction.getInvolvingCapabilities() : Sequence{org.polarsys.capella.core.data.ctx.Capability}

Returns the Sequence of involving capabilities for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getInvolvingCapabilities()
  * the Sequence of involving capabilities for the given SystemFunction

## org.polarsys.capella.core.data.fa.FunctionalChain.getInvolvingCapabilities() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of involving capabilities for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getInvolvingCapabilities()
  * the Sequence of involving capabilities for the given FunctionalChain

## org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement.getInvolvingCapabilityRealizations() : Sequence{org.polarsys.capella.core.data.la.CapabilityRealization}

Returns the Sequence of involving capability realizations for the given CapabilityRealizationInvolvedElement.

### Parameter

* **value**: the CapabilityRealizationInvolvedElement

### Example

* myCapabilityRealizationInvolvedElement.getInvolvingCapabilityRealizations()
  * the Sequence of involving capability realizations for the given CapabilityRealizationInvolvedElement

## org.polarsys.capella.core.data.fa.FunctionalChain.getInvolvingCapabilityRealizations() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of involving capability realizations for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getInvolvingCapabilityRealizations()
  * the Sequence of involving capability realizations for the given FunctionalChain

## org.polarsys.capella.core.data.la.LogicalFunction.getInvolvingCapabilityRealizations() : Sequence{org.polarsys.capella.core.data.la.CapabilityRealization}

Returns the Sequence of involving capability realizations for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getInvolvingCapabilityRealizations()
  * the Sequence of involving capability realizations for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getInvolvingCapabilityRealizations() : Sequence{org.polarsys.capella.core.data.la.CapabilityRealization}

Returns the Sequence of involving capability realizations for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getInvolvingCapabilityRealizations()
  * the Sequence of involving capability realizations for the given PhysicalFunction

## org.polarsys.capella.core.data.capellacore.Involvement.getInvolvingElement() : Sequence{org.polarsys.capella.core.data.capellacore.InvolverElement}

Returns the Sequence of involving elements for the given Involvement.

### Parameter

* **value**: the Involvement

### Example

* myInvolvement.getInvolvingElement()
  * the Sequence of involving elements for the given Involvement

## org.polarsys.capella.core.data.ctx.SystemComponent.getInvolvingMissions() : Sequence{org.polarsys.capella.core.data.ctx.Mission}

Returns the Sequence of involving missions for the given SystemComponent.

### Parameter

* **value**: the SystemComponent

### Example

* mySystemComponent.getInvolvingMissions()
  * the Sequence of involving missions for the given SystemComponent

## org.polarsys.capella.core.data.oa.Entity.getInvolvingOperationalCapabilities() : Sequence{org.polarsys.capella.core.data.oa.OperationalCapability}

Returns the Sequence of involving operation capabilities for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getInvolvingOperationalCapabilities()
  * the Sequence of involving operation capabilities for the given Entity

## org.polarsys.capella.core.data.oa.OperationalProcess.getInvolvingOperationalCapabilities() : Sequence{org.polarsys.capella.core.data.oa.OperationalActivity}

Returns the Sequence of involved operational capabilities for the given OperationalProcess.

### Parameter

* **value**: the OperationalProcess

### Example

* myOperationalProcess.getInvolvingOperationalCapabilities()
  * the Sequence of involved capabilities activities for the given OperationalProcess

## org.polarsys.capella.core.data.cs.Interface.getInvolvingScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of involving scenarii for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getInvolvingScenarios()
  * the Sequence of involving scenarii for the given Interface

## org.polarsys.capella.common.data.modellingcore.IState.getInvolvingStatesAndModes() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of involving states and modes for the given IState.

### Parameter

* **value**: the IState

### Example

* myIState.getInvolvingStatesAndModes()
  * the Sequence of involving states and modes for the given IState

## org.polarsys.capella.core.data.fa.SequenceLink.getLinks() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink}

Returns the Sequence of links for the given SequenceLink.

### Parameter

* **value**: the SequenceLink

### Example

* mySequenceLink.getLinks()
  * the Sequence of links for the given SequenceLink

## org.polarsys.capella.core.data.fa.FunctionalExchange.getOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of operational processes for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getOperationalProcesses()
  * the Sequence of operational processes for the given FunctionalExchange

## org.polarsys.capella.core.data.oa.OperationalActivity.getOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of operational processes for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getOperationalProcesses()
  * the Sequence of operational processes for the given OperationalActivity

## org.polarsys.capella.core.data.ctx.SystemFunction.getOutFlowPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionOutputPort}

Returns the Sequence of out function ports for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getOutFlowPorts()
  * the Sequence of out function ports for the given SystemFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getOutFlowPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionOutputPort}

Returns the Sequence of out flow ports for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getOutFlowPorts()
  * the Sequence of out flow ports for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getOutFlowPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionOutputPort}

Returns the Sequence of outgoing functional exchanges for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getOutFlowPorts()
  * the Sequence of outgoing functional exchanges for the given PhysicalFunction

## org.polarsys.capella.core.data.oa.Entity.getOutgoingCommunicationMean() : Sequence{org.polarsys.capella.core.data.oa.CommunicationMean}

Returns the Sequence of outgoing communication means for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getOutgoingCommunicationMean()
  * the Sequence of outgoing communication means for the given Entity

## org.polarsys.capella.core.data.cs.Component.getOutgoingComponentExchanges() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of outgoing component echanges for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getOutgoingComponentExchanges()
  * the Sequence of outgoing component echanges for the given Component

## org.polarsys.capella.core.data.fa.ComponentPort.getOutgoingComponentExchanges() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of outgoing component exchanges for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getOutgoingComponentExchanges()
  * the Sequence of outgoing component exchanges for the given ComponentPort

## org.polarsys.capella.core.data.cs.Component.getOutgoingDelegations() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of outgoing delegations for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getOutgoingDelegations()
  * the Sequence of outgoing delegations for the given Component

## org.polarsys.capella.core.data.cs.PhysicalPort.getOutgoingDelegations() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of outgoing delegations links for the given PhysicalPort.

### Parameter

* **value**: the PhysicalPort

### Example

* myPhysicalPort.getOutgoingDelegations()
  * the Sequence of outgoing delegations for the given PhysicalPort

## org.polarsys.capella.core.data.fa.ComponentPort.getOutgoingDelegations() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of outgoing delegations for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getOutgoingDelegations()
  * the Sequence of outgoing delegations for the given ComponentPort

## org.polarsys.capella.common.data.activity.Pin.getOutgoingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of outgoing functional exchanges for the given Pin.

### Parameter

* **value**: the Pin

### Example

* myPin.getOutgoingFunctionalExchanges()
  * the Sequence of outgoing functional exchanges for the given Pin

## org.polarsys.capella.core.data.ctx.SystemFunction.getOutgoingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of outgoing functional exchanges for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getOutgoingFunctionalExchanges()
  * the Sequence of outgoing functional exchanges for the given SystemFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getOutgoingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of outgoing functional exchanges for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getOutgoingFunctionalExchanges()
  * the Sequence of outgoing functional exchanges for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getOutgoingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of outgoing functional exchanges for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getOutgoingFunctionalExchanges()
  * the Sequence of outgoing functional exchanges for the given PhysicalFunction

## org.polarsys.capella.common.data.modellingcore.TraceableElement.getOutgoingGenericTraces() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of outgoing generic traces for the given TraceableElement.

### Parameter

* **value**: the TraceableElement

### Example

* myTraceableElement.getOutgoingGenericTraces()
  * the Sequence of outgoing generic traces for the given TraceableElement

## org.polarsys.capella.core.data.oa.OperationalActivity.getOutgoingInteractions() : Sequence{org.polarsys.capella.core.data.fa.AbstractFunction}

Returns the Sequence of outgoing interactions for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getOutgoingInteractions()
  * the Sequence of outgoing interactions for the given OperationalActivity

## org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction.getOutgoingInvolvementLinks() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvement}

Returns the Sequence of outgoing involvement links for the given FunctionalChainInvolvementFunction.

### Parameter

* **value**: the FunctionalChainInvolvementFunction

### Example

* myFunctionalChainInvolvementFunction.getOutgoingInvolvementLinks()
  * the Sequence of outgoing involvement links for the given FunctionalChainInvolvementFunction

## org.polarsys.capella.common.data.modellingcore.IState.getOutgoingTransition() : Sequence{org.polarsys.capella.core.data.capellacommon.StateTransition}

Returns the Sequence of outgoing transitions for the given IState.

### Parameter

* **value**: the IState

### Example

* myIState.getOutgoingTransition()
  * the Sequence of outgoing transitions for the given IState

## org.polarsys.capella.core.data.cs.Component.getOwnedComponents() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of owned components for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getOwnedComponents()
  * the Sequence of owned components for the given Component

## org.polarsys.capella.core.data.capellacommon.Region.getOwnedEntryExitPoints() : Sequence{org.polarsys.capella.core.data.capellacommon.Pseudostate}

Returns the Sequence of owned entry exit points for the given Region.

### Parameter

* **value**: the Region

### Example

* myRegion.getOwnedEntryExitPoints()
  * the Sequence of owned entry exit points for the given Region

## org.polarsys.capella.core.data.ctx.Capability.getOwnedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of owned functional chains for the given Capability.

### Parameter

* **value**: the Capability

### Example

* myCapability.getOwnedFunctionalChains()
  * the Sequence of owned functional chains for the given Capability

## org.polarsys.capella.core.data.ctx.SystemFunction.getOwnedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of owned functional chains for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getOwnedFunctionalChains()
  * the Sequence of owned functional chains for the given SystemFunction

## org.polarsys.capella.core.data.la.CapabilityRealization.getOwnedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of owned functional chains for the given CapabilityRealization.

### Parameter

* **value**: the CapabilityRealization

### Example

* myCapabilityRealization.getOwnedFunctionalChains()
  * the Sequence of owned functional chains for the given CapabilityRealization

## org.polarsys.capella.core.data.la.LogicalFunction.getOwnedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of owned functional chains for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getOwnedFunctionalChains()
  * the Sequence of owned functional chains for the given LogicalFunction

## org.polarsys.capella.core.data.pa.PhysicalFunction.getOwnedFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChain}

Returns the Sequence of owned functional chains for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getOwnedFunctionalChains()
  * the Sequence of owned functional chains for the given PhysicalFunction

## org.polarsys.capella.core.data.oa.OperationalActivity.getOwnedOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of owned operational processes for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getOwnedOperationalProcesses()
  * the Sequence of owned operational processes for the given OperationalActivity

## org.polarsys.capella.core.data.oa.OperationalCapability.getOwnedOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of owned operational processes for the given OperationalCapability.

### Parameter

* **value**: the OperationalCapability

### Example

* myOperationalCapability.getOwnedOperationalProcesses()
  * the Sequence of owned operational processes for the given OperationalCapability

## org.polarsys.capella.common.data.modellingcore.IState.getOwnedStateAndMode() : Sequence{org.polarsys.capella.core.data.capellacommon.AbstractState}

Returns the Sequence of owned state and mode for the given IState.

### Parameter

* **value**: the IState

### Example

* myIState.getOwnedStateAndMode()
  * the Sequence of owned state and mode for the given IState

## org.polarsys.capella.common.data.activity.Pin.getOwner() : Sequence{EObject}

Returns the Sequence of owner for the given Pin.

### Parameter

* **value**: the Pin

### Example

* myPin.getOwner()
  * the Sequence of owner for the given Pin

## org.polarsys.capella.core.data.fa.ComponentExchange.getOwner() : Sequence{EObject}

Returns the Sequence of owner for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getOwner()
  * the Sequence of owner for the given ComponentExchange

## org.polarsys.capella.core.data.fa.ComponentPort.getOwner() : Sequence{EObject}

Returns the Sequence of owner for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getType()
  * the Sequence of owner for the given ComponentPort

## org.polarsys.capella.core.data.fa.FunctionalChain.getOwner() : Sequence{org.polarsys.capella.core.data.fa.AbstractFunction}

Returns the Sequence of owners for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getOwner()
  * the Sequence of owners for the given OperationalActivity

## org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction.getOwner() : Sequence{EObject}

Returns the Sequence of owner for the given FunctionalChainInvolvementFunction.

### Parameter

* **value**: the FunctionalChainInvolvementFunction

### Example

* myFunctionalChainInvolvementFunction.getOwner()
  * the Sequence of owner for the given FunctionalChainInvolvementFunction

## org.polarsys.capella.core.data.fa.FunctionalExchange.getOwner() : Sequence{EObject}

Returns the Sequence of owners for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getOwner()
  * the Sequence of owners for the given FunctionalExchange

## org.polarsys.capella.core.data.fa.SequenceLink.getOwner() : Sequence{EObject}

Returns the Sequence of owner for the given SequenceLink.

### Parameter

* **value**: the SequenceLink

### Example

* mySequenceLink.getOwner()
  * the Sequence of owner for the given SequenceLink

## org.polarsys.capella.core.data.fa.FunctionalChain.getParentFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainReference}

Returns the Sequence of parent functional chains for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getParentFunctionalChains()
  * the Sequence of parent functional chains for the given FunctionalChain

## org.polarsys.capella.core.data.fa.FunctionalChainReference.getParentFunctionalChains() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainReference}

Returns the Sequence of parent fuctional chains for the given FunctionalChainReference.

### Parameter

* **value**: the FunctionalChainReference

### Example

* myFunctionalChainReference.getParentFunctionalChains()
  * the Sequence of parent fuctional chains for the given FunctionalChainReference

## org.polarsys.capella.core.data.oa.OperationalProcess.getParentOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of parent operational processes for the given OperationalProcess.

### Parameter

* **value**: the OperationalProcess

### Example

* myOperationalProcess.getParentOperationalProcesses()
  * the Sequence of parent operational processes for the given OperationalProcess

## org.polarsys.capella.core.data.capellacommon.Pseudostate.getParentRegion() : Sequence{org.polarsys.capella.core.data.capellacommon.Region}

Returns the Sequence of parent region for the given Pseudostate.

### Parameter

* **value**: the Pseudostate

### Example

* myPseudostate.getParentRegion()
  * the Sequence of parent region for the given Pseudostate

## org.polarsys.capella.core.data.interaction.InstanceRole.getParentScenario() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of parent scenario for the given InstanceRole.

### Parameter

* **value**: the InstanceRole

### Example

* myInstanceRole.getParentScenario()
  * the Sequence of parent scenario for the given InstanceRole

## org.polarsys.capella.core.data.interaction.SequenceMessage.getParentScenario() : Sequence{EObject}

Returns the Sequence of parent scenario for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getParentScenario()
  * the Sequence of parent scenario for the given SequenceMessage

## org.polarsys.capella.common.data.modellingcore.IState.getParentStateAndMode() : Sequence{org.polarsys.capella.core.data.capellacommon.State}

Returns the Sequence of parent state and mode for the given IState.

### Parameter

* **value**: the IState

### Example

* myIState.getParentStateAndMode()
  * the Sequence of parent state and mode for the given IState

## org.polarsys.capella.core.data.cs.Component.getParent() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of parent for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getParent()
  * the Sequence of parent for the given Component

## org.polarsys.capella.core.data.fa.AbstractFunction.getParent() : Sequence{org.polarsys.capella.core.data.fa.AbstractFunction}

Returns the Sequence of parents for the given AbstractFunction.

### Parameter

* **value**: the AbstractFunction

### Example

* myAbstractFunction.getParent()
  * the Sequence of parents for the given AbstractFunction

## org.polarsys.capella.core.data.information.Property.getParent() : Sequence{EObject}

Returns the Sequence of parents for the given Property.

### Parameter

* **value**: the Property

### Example

* myProperty.getParent()
  * the Sequence of parents for the given Property

## org.polarsys.capella.core.data.interaction.Scenario.getParent() : Sequence{EObject}

Returns the Sequence of parent for the given Scenario.

### Parameter

* **value**: the Scenario

### Example

* myScenario.getParent()
  * the Sequence of parent for the given Scenario

## org.polarsys.capella.core.data.interaction.SequenceMessage.getPartSource() : Sequence{org.polarsys.capella.core.data.cs.Part}

Returns the Sequence of part source for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getPartSource()
  * the Sequence of part source for the given SequenceMessage

## org.polarsys.capella.core.data.interaction.SequenceMessage.getPartTarget() : Sequence{org.polarsys.capella.core.data.cs.Part}

Returns the Sequence of part target for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getPartTarget()
  * the Sequence of part target for the given SequenceMessage

## org.polarsys.capella.core.data.cs.PhysicalLinkCategory.getPhysicalLinks() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLink}

Returns the Sequence of physical links for the given PhysicalLinkCategory.

### Parameter

* **value**: the PhysicalLinkCategory

### Example

* myPhysicalLinkCategory.getPhysicalLinks()
  * the Sequence of physical links for the given PhysicalLinkCategory

## org.polarsys.capella.core.data.cs.PhysicalLink.getPhysicalPaths() : Sequence{org.polarsys.capella.core.data.cs.PhysicalPath}

Returns the Sequence of physical paths for the given PhysicalLink.

### Parameter

* **value**: the PhysicalLink

### Example

* myPhysicalLink.getPhysicalPaths()
  * the Sequence of physical paths for the given PhysicalLink

## org.polarsys.capella.core.data.cs.PhysicalPath.getPhysicalPaths() : Sequence{org.polarsys.capella.core.data.cs.PhysicalPath}

Returns the Sequence of physical paths for the given PhysicalPath.

### Parameter

* **value**: the PhysicalPath

### Example

* myPhysicalPath.getPhysicalPaths()
  * the Sequence of physical paths for the given PhysicalPath

## org.polarsys.capella.common.data.modellingcore.ModelElement.getPostCondition() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractConstraint}

Returns the Sequence of post consition for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getPostCondition()
  * the Sequence of post consition for the given ModelElement

## org.polarsys.capella.common.data.modellingcore.ModelElement.getPreCondition() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractConstraint}

Returns the Sequence of pre conditions for the given ModelElement.

### Parameter

* **value**: the ModelElement

### Example

* myModelElement.getPreCondition()
  * the Sequence of pre conditions for the given ModelElement

## org.polarsys.capella.core.data.cs.Component.getProvidedInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of provided interfaces for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getProvidedInterfaces()
  * the Sequence of provided interfaces for the given Component

## org.polarsys.capella.core.data.fa.ComponentPort.getProvidedInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of provided interfaces for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getProvidedInterfaces()
  * the Sequence of provided interfaces for the given ComponentPort

## org.polarsys.capella.core.data.cs.Interface.getProviders() : Sequence{org.polarsys.capella.core.data.fa.ComponentPort}

Returns the Sequence of providers for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getProviders()
  * the Sequence of providers for the given Interface

## org.polarsys.kitalpha.emde.model.Element.getRECSourceElement() : Sequence{EObject}

Returns the Sequence of REC source element for the given Element.

### Parameter

* **value**: the Element

### Example

* myElement.getRECSourceElement()
  * the Sequence of REC source element for the given Element

## org.polarsys.kitalpha.emde.model.Element.getREC() : Sequence{org.polarsys.capella.common.re.CatalogElement}

Returns the Sequence of REC for the given Element.

### Parameter

* **value**: the Element

### Example

* myElement.getREC()
  * the Sequence of REC for the given Element

## org.polarsys.capella.common.re.CatalogElement.getRPL() : Sequence{org.polarsys.capella.common.re.CatalogElement}

Returns the Sequence of RPL for the given CatalogElement.

### Parameter

* **value**: the CatalogElement

### Example

* myCatalogElement.getRPL()
  * the Sequence of RPL for the given CatalogElement

## org.polarsys.kitalpha.emde.model.Element.getRPL() : Sequence{org.polarsys.capella.common.re.CatalogElement}

Returns the Sequence of RPL for the given Element.

### Parameter

* **value**: the Element

### Example

* myElement.getRPL()
  * the Sequence of RPL for the given Element

## org.polarsys.capella.core.data.la.CapabilityRealization.getRealizedCapabilities() : Sequence{org.polarsys.capella.core.data.ctx.Capability}

Returns the Sequence of realized capabilities for the given CapabilityRealization.

### Parameter

* **value**: the CapabilityRealization

### Example

* myCapabilityRealization.getRealizedCapabilities()
  * the Sequence of realized capabilities for the given CapabilityRealization

## org.polarsys.capella.core.data.la.CapabilityRealization.getRealizedCapabilityRealizations() : Sequence{org.polarsys.capella.core.data.la.CapabilityRealization}

Returns the Sequence of realized capabilities realizations for the given CapabilityRealization.

### Parameter

* **value**: the CapabilityRealization

### Example

* myCapabilityRealization.getRealizedCapabilityRealizations()
  * the Sequence of realized capabilities realizations for the given CapabilityRealization

## org.polarsys.capella.core.data.information.Class.getRealizedClasses() : Sequence{org.polarsys.capella.core.data.information.Class}

Returns the Sequence of realized classes for the given Class.

### Parameter

* **value**: the Class

### Example

* myClass.getRealizedClasses()
  * the Sequence of realized classes for the given Class

## org.polarsys.capella.core.data.fa.ComponentExchange.getRealizedCommunicationMean() : Sequence{org.polarsys.capella.core.data.oa.CommunicationMean}

Returns the Sequence of realized communication means for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getRealizedCommunicationMean()
  * the Sequence of realized communication means for the given ComponentExchange

## org.polarsys.capella.core.data.fa.ExchangeSpecification.getRealizedComponentExchanges() : Sequence{org.polarsys.capella.core.data.oa.CommunicationMean}

Returns the Sequence of realized system components for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getRealizedComponentExchanges()
  * the Sequence of realized system components for the given Entity

## org.polarsys.capella.core.data.fa.ComponentPort.getRealizedComponentPorts() : Sequence{org.polarsys.capella.core.data.fa.ComponentPort}

Returns the Sequence of realized component ports for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getRealizedComponentPorts()
  * the Sequence of realized component ports for the given ComponentPort

## org.polarsys.capella.core.data.information.ExchangeItem.getRealizedExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of realized echange items for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getRealizedExchangeItems()
  * the Sequence of realized echange items for the given ExchangeItem

## org.polarsys.capella.core.data.fa.FunctionPort.getRealizedFunctionPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionPort}

Returns the Sequence of realized function ports for the given FunctionPort.

### Parameter

* **value**: the FunctionPort

### Example

* myFunctionPort.getRealizedFunctionPorts()
  * the Sequence of realized function ports for the given FunctionPort

## org.polarsys.capella.core.data.fa.FunctionalChain.getRealizedFunctionalChains() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of realized functional chains for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getRealizedFunctionalChains()
  * the Sequence of realized functional chains for the given FunctionalChain

## org.polarsys.capella.core.data.fa.FunctionalExchange.getRealizedFunctionalExchange() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of realized functional exchanges for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getRealizedFunctionalExchange()
  * the Sequence of realized functional exchanges for the given FunctionalExchange

## org.polarsys.capella.core.data.fa.FunctionalExchange.getRealizedInteractions() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of realized interactions for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getRealizedInteractions()
  * the Sequence of realized interactions for the given FunctionalExchange

## org.polarsys.capella.core.data.pa.PhysicalComponent.getRealizedLogicalComponent() : Sequence{org.polarsys.capella.core.data.la.LogicalComponent}

Returns the Sequence of realized logical components for the given PhysicalComponent.

### Parameter

* **value**: the PhysicalComponent

### Example

* myPhysicalComponent.getRealizedLogicalComponent()
  * the Sequence of realized logical components for the given PhysicalComponent

## org.polarsys.capella.core.data.pa.PhysicalFunction.getRealizedLogicalFunctions() : Sequence{org.polarsys.capella.core.data.la.LogicalFunction}

Returns the Sequence of realized logical functions for the given PhysicalFunction.

### Parameter

* **value**: the PhysicalFunction

### Example

* myPhysicalFunction.getRealizedLogicalFunctions()
  * the Sequence of realized logical functions for the given PhysicalFunction

## org.polarsys.capella.core.data.capellacommon.AbstractState.getRealizedMode() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of realized mode for the given AbstractState.

### Parameter

* **value**: the AbstractState

### Example

* myAbstractState.getRealizedMode()
  * the Sequence of realized mode for the given AbstractState

## org.polarsys.capella.core.data.ctx.SystemFunction.getRealizedOperationalActivities() : Sequence{org.polarsys.capella.core.data.oa.OperationalActivity}

Returns the Sequence of realized operational activities for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getRealizedOperationalActivities()
  * the Sequence of realized operational activities for the given SystemFunction

## org.polarsys.capella.core.data.ctx.Capability.getRealizedOperationalCapabilities() : Sequence{org.polarsys.capella.core.data.oa.OperationalCapability}

Returns the Sequence of realized operational capabilities for the given Capability.

### Parameter

* **value**: the Capability

### Example

* myCapability.getRealizedOperationalCapabilities()
  * the Sequence of realized operational capabilities for the given Capability

## org.polarsys.capella.core.data.ctx.SystemComponent.getRealizedOperationalEntities() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of realized operation entities for the given SystemComponent.

### Parameter

* **value**: the SystemComponent

### Example

* mySystemComponent.getRealizedOperationalEntities()
  * the Sequence of realized operation entities for the given SystemComponent

## org.polarsys.capella.core.data.fa.FunctionalChain.getRealizedOperationalProcesses() : Sequence{org.polarsys.capella.core.data.oa.OperationalProcess}

Returns the Sequence of realized operational processes for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getRealizedOperationalProcesses()
  * the Sequence of realized operational processes for the given FunctionalChain

## org.polarsys.capella.core.data.epbs.ConfigurationItem.getRealizedPhysicalComponents() : Sequence{org.polarsys.capella.core.data.pa.PhysicalComponent}

Returns the Sequence of realized physical components for the given ConfigurationItem.

### Parameter

* **value**: the ConfigurationItem

### Example

* myConfigurationItem.getRealizedPhysicalComponents()
  * the Sequence of realized physical components for the given ConfigurationItem

## org.polarsys.capella.core.data.epbs.ConfigurationItem.getRealizedPhysicalLinks() : Sequence{org.polarsys.capella.core.data.cs.PhysicalLink}

Returns the Sequence of realized physical links for the given ConfigurationItem.

### Parameter

* **value**: the ConfigurationItem

### Example

* myConfigurationItem.getRealizedPhysicalLinks()
  * the Sequence of realized physical links for the given ConfigurationItem

## org.polarsys.capella.core.data.epbs.ConfigurationItem.getRealizedPhysicalPorts() : Sequence{org.polarsys.capella.core.data.cs.PhysicalPort}

Returns the Sequence of realized physical ports for the given ConfigurationItem.

### Parameter

* **value**: the ConfigurationItem

### Example

* myConfigurationItem.getRealizedPhysicalLinks()
  * the Sequence of realized physical ports for the given ConfigurationItem

## org.polarsys.capella.common.data.activity.Pin.getRealizedPin() : Sequence{EObject}

Returns the Sequence of realized pins for the given Pin.

### Parameter

* **value**: the Pin

### Example

* myPin.getRealizedPin()
  * the Sequence of realized pins for the given Pin

## org.polarsys.capella.core.data.interaction.Scenario.getRealizedScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of realized scenarii for the given Scenario.

### Parameter

* **value**: the Scenario

### Example

* myScenario.getRealizedScenarios()
  * the Sequence of realized scenarii for the given Scenario

## org.polarsys.capella.core.data.capellacommon.StateTransition.getRealizedStateTransition() : Sequence{org.polarsys.capella.core.data.capellacommon.StateTransition}

Returns the Sequence of realized state transition for the given StateTransition.

### Parameter

* **value**: the StateTransition

### Example

* myStateTransition.getRealizedStateTransition()
  * the Sequence of realized state transition for the given StateTransition

## org.polarsys.capella.core.data.capellacommon.AbstractState.getRealizedState() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of realized state for the given AbstractState.

### Parameter

* **value**: the AbstractState

### Example

* myAbstractState.getRealizedState()
  * the Sequence of realized state for the given AbstractState

## org.polarsys.capella.core.data.la.LogicalComponent.getRealizedSystemComponents() : Sequence{org.polarsys.capella.core.data.ctx.SystemComponent}

Returns the Sequence of realized system components for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getRealizedSystemComponents()
  * the Sequence of realized system components for the given LogicalFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getRealizedSystemFunctions() : Sequence{org.polarsys.capella.core.data.ctx.SystemFunction}

Returns the Sequence of realized system functions for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getRealizedSystemFunctions()
  * the Sequence of realized system functions for the given LogicalFunction

## org.polarsys.capella.core.data.oa.OperationalCapability.getRealizingCapabilities() : Sequence{org.polarsys.capella.core.data.ctx.Capability}

Returns the Sequence of realizing capabilities for the given OperationalCapability.

### Parameter

* **value**: the OperationalCapability

### Example

* myOperationalCapability.getRealizingCapabilities()
  * the Sequence of realizing capabilities for the given OperationalCapability

## org.polarsys.capella.core.data.ctx.Capability.getRealizingCapabilityRealizations() : Sequence{org.polarsys.capella.core.data.la.CapabilityRealization}

Returns the Sequence of realizing capability realizations for the given Capability.

### Parameter

* **value**: the Capability

### Example

* myCapability.getRealizingCapabilityRealizations()
  * the Sequence of realizing capability realizations for the given Capability

## org.polarsys.capella.core.data.la.CapabilityRealization.getRealizingCapabilityRealizations() : Sequence{org.polarsys.capella.core.data.la.CapabilityRealization}

Returns the Sequence of realizing capability realizations for the given CapabilityRealization.

### Parameter

* **value**: the CapabilityRealization

### Example

* myCapabilityRealization.getRealizingCapabilityRealizations()
  * the Sequence of realizing capability realizations for the given CapabilityRealization

## org.polarsys.capella.core.data.information.Class.getRealizingClasses() : Sequence{org.polarsys.capella.core.data.information.Class}

Returns the Sequence of realizing classes for the given Class.

### Parameter

* **value**: the Class

### Example

* myClass.getRealizingClasses()
  * the Sequence of realizing classes for the given Class

## org.polarsys.capella.core.data.fa.ComponentExchange.getRealizingComponentExchanges() : Sequence{org.polarsys.capella.core.data.fa.ComponentExchange}

Returns the Sequence of realizing component exchanges for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getRealizingComponentExchanges()
  * the Sequence of realizing component exchanges for the given ComponentExchange

## org.polarsys.capella.core.data.fa.ComponentPort.getRealizingComponentPorts() : Sequence{org.polarsys.capella.core.data.information.Port}

Returns the Sequence of realizing component ports for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getRealizingComponentPorts()
  * the Sequence of realizing component ports for the given ComponentPort

## org.polarsys.capella.core.data.pa.PhysicalComponent.getRealizingComponents() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of realizing components for the given PhysicalComponent.

### Parameter

* **value**: the PhysicalComponent

### Example

* myPhysicalComponent.getRealizingComponents()
  * the Sequence of realizing components for the given PhysicalComponent

## org.polarsys.capella.core.data.cs.PhysicalLink.getRealizingConfigurationItems() : Sequence{org.polarsys.capella.core.data.epbs.ConfigurationItem}

Returns the Sequence of realizing configuration items for the given PhysicalLink.

### Parameter

* **value**: the PhysicalLink

### Example

* myPhysicalLink.getRealizingConfigurationItems()
  * the Sequence of realizing configuration items for the given PhysicalLink

## org.polarsys.capella.core.data.cs.PhysicalPort.getRealizingConfigurationItems() : Sequence{org.polarsys.capella.core.data.epbs.ConfigurationItem}

Returns the Sequence of realizing configuration items for the given PhysicalPort.

### Parameter

* **value**: the PhysicalPort

### Example

* myPhysicalPort.getRealizingConfigurationItems()
  * the Sequence of realizing configuration items for the given PhysicalPort

## org.polarsys.capella.core.data.pa.PhysicalComponent.getRealizingConfigurationItems() : Sequence{org.polarsys.capella.core.data.epbs.ConfigurationItem}

Returns the Sequence of realizing configuration items for the given PhysicalComponent.

### Parameter

* **value**: the PhysicalComponent

### Example

* myPhysicalComponent.getRealizingConfigurationItems()
  * the Sequence of realizing configuration items for the given PhysicalComponent

## org.polarsys.capella.core.data.information.ExchangeItem.getRealizingExchangeItems() : Sequence{org.polarsys.capella.core.data.information.ExchangeItem}

Returns the Sequence of realizing exchange items for the given ExchangeItem.

### Parameter

* **value**: the ExchangeItem

### Example

* myExchangeItem.getRealizingExchangeItems()
  * the Sequence of realizing exchange items for the given ExchangeItem

## org.polarsys.capella.core.data.fa.FunctionPort.getRealizingFunctionPorts() : Sequence{org.polarsys.capella.core.data.fa.FunctionPort}

Returns the Sequence of realizing function ports for the given FunctionPort.

### Parameter

* **value**: the FunctionPort

### Example

* myFunctionPort.getRealizingFunctionPorts()
  * the Sequence of realizing function ports for the given FunctionPort

## org.polarsys.capella.core.data.fa.FunctionalChain.getRealizingFunctionalChains() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of realizing functional chains for the given FunctionalChain.

### Parameter

* **value**: the FunctionalChain

### Example

* myFunctionalChain.getRealizingFunctionalChains()
  * the Sequence of realizing functional chains for the given FunctionalChain

## org.polarsys.capella.core.data.fa.FunctionalExchange.getRealizingFunctionalExchanges() : Sequence{org.polarsys.capella.core.data.fa.FunctionalExchange}

Returns the Sequence of realizing functional exchanges for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getRealizingFunctionalExchanges()
  * the Sequence of realizing functional exchanges for the given FunctionalExchange

## org.polarsys.capella.core.data.ctx.SystemComponent.getRealizingLogicalComponents() : Sequence{org.polarsys.capella.core.data.la.LogicalComponent}

Returns the Sequence of realizing logical components for the given SystemComponent.

### Parameter

* **value**: the SystemComponent

### Example

* mySystemComponent.getRealizingLogicalComponents()
  * the Sequence of realizing logical components for the given SystemComponent

## org.polarsys.capella.core.data.ctx.SystemFunction.getRealizingLogicalFunctions() : Sequence{org.polarsys.capella.core.data.la.LogicalFunction}

Returns the Sequence of realizing logical functions for the given SystemFunction.

### Parameter

* **value**: the SystemFunction

### Example

* mySystemFunction.getRealizingLogicalFunctions()
  * the Sequence of realizing logical functions for the given SystemFunction

## org.polarsys.capella.core.data.capellacommon.AbstractState.getRealizingMode() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of realizing mode for the given AbstractState.

### Parameter

* **value**: the AbstractState

### Example

* myAbstractState.getRealizingMode()
  * the Sequence of realizing mode for the given AbstractState

## org.polarsys.capella.core.data.la.LogicalComponent.getRealizingPhysicalComponents() : Sequence{org.polarsys.capella.core.data.pa.PhysicalComponent}

Returns the Sequence of realizing physical components for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getRealizingPhysicalComponents()
  * the Sequence of realizing physical components for the given LogicalFunction

## org.polarsys.capella.core.data.la.LogicalFunction.getRealizingPhysicalFunctions() : Sequence{org.polarsys.capella.core.data.pa.PhysicalFunction}

Returns the Sequence of realizing physical functions for the given LogicalFunction.

### Parameter

* **value**: the LogicalFunction

### Example

* myLogicalFunction.getRealizingPhysicalFunctions()
  * the Sequence of realizing physical functions for the given LogicalFunction

## org.polarsys.capella.common.data.activity.Pin.getRealizingPin() : Sequence{EObject}

Returns the Sequence of realizing pins for the given Pin.

### Parameter

* **value**: the Pin

### Example

* myPin.getIncomingFunctionalExchanges()
  * the Sequence of realizing pins for the given Pin

## org.polarsys.capella.core.data.interaction.Scenario.getRealizingScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of realizing scenarii for the given Scenario.

### Parameter

* **value**: the Scenario

### Example

* myScenario.getRealizingScenarios()
  * the Sequence of realizing scenarii for the given Scenario

## org.polarsys.capella.core.data.capellacommon.StateTransition.getRealizingStateTransition() : Sequence{org.polarsys.capella.core.data.capellacommon.StateTransition}

Returns the Sequence of realizing state transition for the given StateTransition.

### Parameter

* **value**: the StateTransition

### Example

* myStateTransition.getRealizingStateTransition()
  * the Sequence of realizing state transition for the given StateTransition

## org.polarsys.capella.core.data.capellacommon.AbstractState.getRealizingState() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of realizing state for the given AbstractState.

### Parameter

* **value**: the AbstractState

### Example

* myAbstractState.getRealizingState()
  * the Sequence of realizing state for the given AbstractState

## org.polarsys.capella.core.data.oa.Entity.getRealizingSystemComponents() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of realizing system components for the given Entity.

### Parameter

* **value**: the Entity

### Example

* myEntity.getRealizingSystemComponents()
  * the Sequence of realizing system components for the given Entity

## org.polarsys.capella.core.data.oa.OperationalActivity.getRealizingSystemFunctions() : Sequence{org.polarsys.capella.core.data.ctx.SystemFunction}

Returns the Sequence of realizing system functions for the given OperationalActivity.

### Parameter

* **value**: the OperationalActivity

### Example

* myOperationalActivity.getRealizingSystemFunctions()
  * the Sequence of realizing system functions for the given OperationalActivity

## org.polarsys.capella.common.re.CatalogElementLink.getReferencedElement() : Sequence{EObject}

Returns the Sequence of referenced element for the given CatalogElementLink.

### Parameter

* **value**: the CatalogElementLink

### Example

* myCatalogElementLink.getReferencedElement()
  * the Sequence of referenced element for the given CatalogElementLink

## org.polarsys.capella.core.data.information.datavalue.DataValue.getReferencedProperty() : Sequence{org.polarsys.capella.core.data.information.Property}

Returns the Sequence of referenced properties for the given DataValue.

### Parameter

* **value**: the DataValue

### Example

* myDataValue.getReferencedProperty()
  * the Sequence of referenced properties for the given DataValue

## org.polarsys.capella.core.data.interaction.InteractionUse.getReferencedScenario() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of referenced senario for the given InteractionUse.

### Parameter

* **value**: the InteractionUse

### Example

* myInteractionUse.getReferencedScenario()
  * the Sequence of referenced senario for the given InteractionUse

## org.polarsys.capella.core.data.interaction.Scenario.getReferencedScenario() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of referenced scenario for the given Scenario.

### Parameter

* **value**: the Scenario

### Example

* myScenario.getReferencedScenario()
  * the Sequence of referenced scenario for the given Scenario

## org.polarsys.capella.core.data.information.datavalue.DataValue.getReferencedValue() : Sequence{org.polarsys.capella.core.data.information.datavalue.DataValue}

Returns the Sequence of referenced values for the given DataValue.

### Parameter

* **value**: the DataValue

### Example

* myDataValue.getReferencedValue()
  * the Sequence of referenced values for the given DataValue

## org.polarsys.capella.core.data.cs.Component.getReferencingComponents() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of referencing components for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getReferencingComponents()
  * the Sequence of referencing components for the given Component

## org.polarsys.capella.core.data.interaction.InteractionUse.getReferencingScenario() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of referencing senario for the given InteractionUse.

### Parameter

* **value**: the InteractionUse

### Example

* myInteractionUse.getReferencingScenario()
  * the Sequence of referencing senario for the given InteractionUse

## org.polarsys.capella.core.data.information.datavalue.DataValue.getReferencingValue() : Sequence{Object}

Returns the Sequence of referencing values for the given DataValue.

### Parameter

* **value**: the DataValue

### Example

* myDataValue.getReferencingValue()
  * the Sequence of referencing values for the given DataValue

## org.polarsys.capella.core.data.interaction.AbstractCapability.getRefinedCapabilities() : Sequence{org.polarsys.capella.core.data.capellacore.CapellaElement}

Returns the Sequence of refined capabilities for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getRefinedCapabilities()
  * the Sequence of refined capabilities for the given AbstractCapability

## org.polarsys.capella.core.data.cs.Interface.getRefinedInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of refined interfaces for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getRefinedInterfaces()
  * the Sequence of refined interfaces for the given Interface

## org.polarsys.capella.core.data.interaction.Scenario.getRefinedScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of refined scenarii for the given Scenario.

### Parameter

* **value**: the Scenario

### Example

* myScenario.getRefinedScenarios()
  * the Sequence of refined scenarii for the given Scenario

## org.polarsys.capella.core.data.interaction.SequenceMessage.getRefinedSequenceMessage() : Sequence{org.polarsys.capella.core.data.interaction.SequenceMessage}

Returns the Sequence of refined sequence message for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getRefinedSequenceMessage()
  * the Sequence of refined sequence message for the given SequenceMessage

## org.polarsys.capella.core.data.cs.Interface.getRefiningInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of refining interfaces for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getRefiningInterfaces()
  * the Sequence of refining interfaces for the given Interface

## org.polarsys.capella.core.data.interaction.AbstractCapability.getRefiningScenarios() : Sequence{org.polarsys.capella.core.data.capellacore.CapellaElement}

Returns the Sequence of refining scenarii for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getRefiningScenarios()
  * the Sequence of refining scenarii for the given AbstractCapability

## org.polarsys.capella.core.data.interaction.Scenario.getRefiningScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of refining scenarii for the given Scenario.

### Parameter

* **value**: the Scenario

### Example

* myScenario.getRefiningScenarios()
  * the Sequence of refining scenarii for the given Scenario

## org.polarsys.capella.core.data.interaction.SequenceMessage.getRefiningSequenceMessage() : Sequence{org.polarsys.capella.core.data.interaction.SequenceMessage}

Returns the Sequence of refining sequence message for the given SequenceMessage.

### Parameter

* **value**: the SequenceMessage

### Example

* mySequenceMessage.getRefiningSequenceMessage()
  * the Sequence of refining sequence message for the given SequenceMessage

## org.polarsys.capella.core.data.fa.ComponentExchange.getRelatedData() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractType}

Returns the Sequence of related data for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getRelatedData()
  * the Sequence of related data for the given ComponentExchange

## org.polarsys.capella.core.data.fa.FunctionalExchange.getRelatedData() : Sequence{org.polarsys.capella.core.data.capellacore.Type}

Returns the Sequence of related data for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getIncomingFunctionalExchanges()
  * the Sequence of related data for the given FunctionalExchange

## org.polarsys.capella.common.re.CatalogElement.getRelatedElements() : Sequence{EObject}

Returns the Sequence of related elements for the given CatalogElement.

### Parameter

* **value**: the CatalogElement

### Example

* myCatalogElement.getRelatedElements()
  * the Sequence of related elements for the given CatalogElement

## org.polarsys.capella.core.data.interaction.StateFragment.getRelatedFunction() : Sequence{org.polarsys.capella.core.data.fa.AbstractFunction}

Returns the Sequence of related function for the given StateFragment.

### Parameter

* **value**: the StateFragment

### Example

* myStateFragment.getRelatedFunction()
  * the Sequence of related function for the given StateFragment

## org.polarsys.capella.common.re.CatalogElement.getRelatedReplicableElements() : Sequence{org.polarsys.capella.common.re.CatalogElement}

Returns the Sequence of related replicable elements for the given CatalogElement.

### Parameter

* **value**: the CatalogElement

### Example

* myCatalogElement.getRelatedReplicableElements()
  * the Sequence of related replicable elements for the given CatalogElement

## org.polarsys.capella.core.data.interaction.StateFragment.getRelatedState() : Sequence{org.polarsys.capella.core.data.capellacommon.AbstractState}

Returns the Sequence of related stat for the given StateFragment.

### Parameter

* **value**: the StateFragment

### Example

* myStateFragment.getRelatedState()
  * the Sequence of related stat for the given StateFragment

## org.polarsys.capella.core.data.interaction.InstanceRole.getRepresentedInstance() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractType}

Returns the Sequence of represented instance for the given InstanceRole.

### Parameter

* **value**: the InstanceRole

### Example

* myInstanceRole.getRepresentedInstance()
  * the Sequence of represented instance for the given InstanceRole

## org.polarsys.capella.core.data.cs.Component.getRepresentingParts() : Sequence{org.polarsys.capella.core.data.cs.Part}

Returns the Sequence of representing parts for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getOutgoingComponentExchanges()
  * the Sequence of representing parts for the given Component

## org.polarsys.capella.core.data.cs.Component.getRequiredInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of required interfaces for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getRequiredInterfaces()
  * the Sequence of required interfaces for the given Component

## org.polarsys.capella.core.data.fa.ComponentPort.getRequiredInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of required interfaces for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getRequiredInterfaces()
  * the Sequence of required interfaces for the given ComponentPort

## org.polarsys.capella.core.data.cs.Interface.getRequirers() : Sequence{org.polarsys.capella.core.data.fa.ComponentPort}

Returns the Sequence of requirers for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getRequirers()
  * the Sequence of requirers for the given Interface

## org.polarsys.capella.core.data.information.Association.getRoles() : Sequence{org.polarsys.capella.core.data.information.Property}

Returns the Sequence of roles for the given Association.

### Parameter

* **value**: the Association

### Example

* myAssociation.getRoles()
  * the Sequence of roles for the given Association

## org.polarsys.capella.core.data.capellacommon.State.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given State.

### Parameter

* **value**: the State

### Example

* myState.getScenarios()
  * the Sequence of scenarii for the given State

## org.polarsys.capella.core.data.cs.Component.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getScenarios()
  * the Sequence of scenarii for the given Component

## org.polarsys.capella.core.data.cs.ExchangeItemAllocation.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given ExchangeItemAllocation.

### Parameter

* **value**: the ExchangeItemAllocation

### Example

* myExchangeItemAllocation.getScenarios()
  * the Sequence of scenarii for the given ExchangeItemAllocation

## org.polarsys.capella.core.data.fa.AbstractFunction.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarios for the given AbstractFunction.

### Parameter

* **value**: the AbstractFunction

### Example

* myAbstractFunction.getScenarios()
  * the Sequence of scenarios for the given AbstractFunction

## org.polarsys.capella.core.data.fa.ComponentExchange.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given ComponentExchange.

### Parameter

* **value**: the ComponentExchange

### Example

* myComponentExchange.getScenarios()
  * the Sequence of scenarii for the given ComponentExchange

## org.polarsys.capella.core.data.fa.FunctionalExchange.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of categories for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getScenarios()
  * the Sequence of categories for the given FunctionalExchange

## org.polarsys.capella.core.data.information.ExchangeItemInstance.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given ExchangeItemInstance.

### Parameter

* **value**: the ExchangeItemInstance

### Example

* myExchangeItemInstance.getType()
  * the Sequence of scenarii for the given ExchangeItemInstance

## org.polarsys.capella.core.data.information.Operation.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given Operation.

### Parameter

* **value**: the Operation

### Example

* myOperation.getScenarios()
  * the Sequence of scenarii for the given Operation

## org.polarsys.capella.core.data.interaction.AbstractCapability.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given AbstractCapability.

### Parameter

* **value**: the AbstractCapability

### Example

* myAbstractCapability.getScenarios()
  * the Sequence of scenarii for the given AbstractCapability

## org.polarsys.capella.core.data.oa.Role.getScenarios() : Sequence{org.polarsys.capella.core.data.interaction.Scenario}

Returns the Sequence of scenarii for the given Role.

### Parameter

* **value**: the Role

### Example

* myAllocation.getScenarios()
  * the Sequence of scenarii for the given Role

## org.polarsys.capella.core.data.fa.SequenceLink.getSourceControlNode() : Sequence{org.polarsys.capella.core.data.fa.ControlNode}

Returns the Sequence of source control node for the given SequenceLink.

### Parameter

* **value**: the SequenceLink

### Example

* mySequenceLink.getSourceControlNode()
  * the Sequence of source control node for the given SequenceLink

## org.polarsys.capella.core.data.fa.SequenceLink.getSourceInvolvementFunction() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction}

Returns the Sequence of source involvement functions for the given SequenceLink.

### Parameter

* **value**: the SequenceLink

### Example

* mySequenceLink.getSourceInvolvementFunction()
  * the Sequence of source involvement functions for the given SequenceLink

## org.polarsys.capella.core.data.fa.ReferenceHierarchyContext.getSourceReferenceHierachy() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainReference}

Returns the Sequence of source reference hierarchy for the given ReferenceHierarchyContext.

### Parameter

* **value**: the ReferenceHierarchyContext

### Example

* myReferenceHierarchyContext.getSourceReferenceHierachy()
  * the Sequence of source reference hierarchy for the given ReferenceHierarchyContext

## org.polarsys.capella.core.data.fa.SequenceLinkEnd.getSourceSequenceLinks() : Sequence{org.polarsys.capella.core.data.fa.SequenceLink}

Returns the Sequence of source sequence links for the given SequenceLinkEnd.

### Parameter

* **value**: the SequenceLinkEnd

### Example

* mySequenceLinkEnd.getTargetSequenceLinks()
  * the Sequence of source sequence links for the given SequenceLinkEnd

## org.polarsys.capella.core.data.capellacommon.StateTransition.getSource() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of source for the given StateTransition.

### Parameter

* **value**: the StateTransition

### Example

* myStateTransition.getSource()
  * the Sequence of source for the given StateTransition

## org.polarsys.capella.core.data.capellacore.Allocation.getSource() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of source for the given Allocation.

### Parameter

* **value**: the Allocation

### Example

* myAllocation.getSource()
  * the Sequence of source for the given Allocation

## org.polarsys.capella.core.data.capellacore.Generalization.getSource() : Sequence{org.polarsys.capella.core.data.capellacore.GeneralizableElement}

Returns the Sequence of source for the given Generalization.

### Parameter

* **value**: the Generalization

### Example

* myGeneralization.getSource()
  * the Sequence of source for the given Generalization

## org.polarsys.capella.core.data.capellacore.Trace.getSource() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of sources for the given Trace.

### Parameter

* **value**: the Trace

### Example

* myTrace.getSource()
  * the Sequence of sources for the given Trace

## org.polarsys.capella.core.data.cs.InterfaceImplementation.getSource() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of sources for the given InterfaceImplementation.

### Parameter

* **value**: the InterfaceImplementation

### Example

* myInterface.getSource()
  * the Sequence of sources for the given InterfaceImplementation

## org.polarsys.capella.core.data.cs.InterfaceUse.getSource() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of sources for the given InterfaceUse.

### Parameter

* **value**: the InterfaceUse

### Example

* myInterfaceUse.getSource()
  * the Sequence of sources for the given InterfaceUse

## org.polarsys.capella.core.data.ctx.CapabilityExploitation.getSource() : Sequence{org.polarsys.capella.core.data.ctx.Mission}

Returns the Sequence of source for the given CapabilityExploitation.

### Parameter

* **value**: the CapabilityExploitation

### Example

* myCapabilityExploitation.getSource()
  * the Sequence of source for the given CapabilityExploitation

## org.polarsys.capella.core.data.fa.ExchangeSpecification.getSource() : Sequence{org.polarsys.capella.common.data.modellingcore.InformationsExchanger}

Returns the Sequence of source for the given ExchangeSpecification.

### Parameter

* **value**: the ExchangeSpecification

### Example

* myEntity.getSource()
  * the Sequence of source for the given ExchangeSpecification

## org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink.getSource() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction}

Returns the Sequence of source for the given FunctionalChainInvolvementLink.

### Parameter

* **value**: the FunctionalChainInvolvementLink

### Example

* myFunctionalChainInvolvementLink.getSource()
  * the Sequence of source for the given FunctionalChainInvolvementLink

## org.polarsys.capella.core.data.fa.FunctionalExchange.getSource() : Sequence{org.polarsys.capella.common.data.activity.ActivityNode}

Returns the Sequence of sources for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getSource()
  * the Sequence of sources for the given FunctionalExchange

## org.polarsys.capella.core.data.information.communication.CommunicationLink.getSource() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of source for the given CommunicationLink.

### Parameter

* **value**: the CommunicationLink

### Example

* myCommunicationLink.getSource()
  * the Sequence of source for the given CommunicationLink

## org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend.getSource() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of source for the given AbstractCapabilityExtend.

### Parameter

* **value**: the AbstractCapabilityExtend

### Example

* myAbstractCapabilityExtend.getSource()
  * the Sequence of source for the given AbstractCapabilityExtend

## org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization.getSource() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of source for the given AbstractCapabilityGeneralization.

### Parameter

* **value**: the AbstractCapabilityGeneralization

### Example

* myAbstractCapabilityGeneralization.getSource()
  * the Sequence of source for the given AbstractCapabilityGeneralization

## org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude.getSource() : Sequence{Object}

Returns the Sequence of source for the given AbstractCapabilityInclude.

### Parameter

* **value**: the AbstractCapabilityInclude

### Example

* myAbstractCapabilityInclude.getSource()
  * the Sequence of source for the given AbstractCapabilityInclude

## org.polarsys.capella.core.data.oa.CommunicationMean.getSource() : Sequence{org.polarsys.capella.common.data.modellingcore.InformationsExchanger}

Returns the Sequence of source for the given CommunicationMean.

### Parameter

* **value**: the CommunicationMean

### Example

* myCommunicationMean.getSource()
  * the Sequence of source for the given CommunicationMean

## org.polarsys.capella.core.data.fa.SequenceLink.getTargetControlNode() : Sequence{org.polarsys.capella.core.data.fa.ControlNode}

Returns the Sequence of target control node for the given SequenceLink.

### Parameter

* **value**: the SequenceLink

### Example

* mySequenceLink.getTargetControlNode()
  * the Sequence of target control node for the given SequenceLink

## org.eclipse.sirius.viewpoint.DRepresentationDescriptor.getTargetElement() : Sequence{EObject}

Returns the Sequence of target element for the given DRepresentationDescriptor.

### Parameter

* **value**: the DRepresentationDescriptor

### Example

* myDRepresentationDescriptor.getTargetElement()
  * the Sequence of target element for the given DRepresentationDescriptor

## org.polarsys.capella.core.data.fa.SequenceLink.getTargetInvolvementFunction() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction}

Returns the Sequence of target involvement functions for the given SequenceLink.

### Parameter

* **value**: the SequenceLink

### Example

* mySequenceLink.getTargetInvolvementFunction()
  * the Sequence of target involvement functions for the given SequenceLink

## org.polarsys.capella.core.data.fa.ReferenceHierarchyContext.getTargetReferenceHierachy() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainReference}

Returns the Sequence of target reference hierarchy for the given ReferenceHierarchyContext.

### Parameter

* **value**: the ReferenceHierarchyContext

### Example

* myReferenceHierarchyContext.getTargetReferenceHierachy()
  * the Sequence of target reference hierarchy for the given ReferenceHierarchyContext

## org.polarsys.capella.core.data.fa.SequenceLinkEnd.getTargetSequenceLinks() : Sequence{org.polarsys.capella.core.data.fa.SequenceLink}

Returns the Sequence of target sequence links for the given SequenceLinkEnd.

### Parameter

* **value**: the SequenceLinkEnd

### Example

* mySequenceLinkEnd.getTargetSequenceLinks()
  * the Sequence of target sequence links for the given SequenceLinkEnd

## org.polarsys.capella.core.data.capellacommon.StateTransition.getTarget() : Sequence{org.polarsys.capella.common.data.modellingcore.IState}

Returns the Sequence of target for the given StateTransition.

### Parameter

* **value**: the StateTransition

### Example

* myStateTransition.getTarget()
  * the Sequence of target for the given StateTransition

## org.polarsys.capella.core.data.capellacore.Allocation.getTarget() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of target for the given Allocation.

### Parameter

* **value**: the Allocation

### Example

* myAllocation.getTarget()
  * the Sequence of target for the given Allocation

## org.polarsys.capella.core.data.capellacore.Generalization.getTarget() : Sequence{org.polarsys.capella.core.data.capellacore.GeneralizableElement}

Returns the Sequence of target for the given Generalization.

### Parameter

* **value**: the Generalization

### Example

* myGeneralization.getTarget()
  * the Sequence of target for the given Generalization

## org.polarsys.capella.core.data.capellacore.Trace.getTarget() : Sequence{org.polarsys.capella.common.data.modellingcore.TraceableElement}

Returns the Sequence of targets for the given Trace.

### Parameter

* **value**: the Trace

### Example

* myTrace.getTarget()
  * the Sequence of targets for the given Trace

## org.polarsys.capella.core.data.cs.InterfaceImplementation.getTarget() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of targets for the given InterfaceImplementation.

### Parameter

* **value**: the InterfaceImplementation

### Example

* myInterface.getTarget()
  * the Sequence of targets for the given InterfaceImplementation

## org.polarsys.capella.core.data.cs.InterfaceUse.getTarget() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of targets for the given InterfaceUse.

### Parameter

* **value**: the InterfaceUse

### Example

* myInterfaceUse.getTarget()
  * the Sequence of targets for the given InterfaceUse

## org.polarsys.capella.core.data.fa.ExchangeSpecification.getTarget() : Sequence{org.polarsys.capella.common.data.modellingcore.InformationsExchanger}

Returns the Sequence of target for the given ExchangeSpecification.

### Parameter

* **value**: the ExchangeSpecification

### Example

* myEntity.getTarget()
  * the Sequence of target for the given ExchangeSpecification

## org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink.getTarget() : Sequence{org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction}

Returns the Sequence of target for the given FunctionalChainInvolvementLink.

### Parameter

* **value**: the FunctionalChainInvolvementLink

### Example

* myFunctionalChainInvolvementLink.getTarget()
  * the Sequence of target for the given FunctionalChainInvolvementLink

## org.polarsys.capella.core.data.fa.FunctionalExchange.getTarget() : Sequence{org.polarsys.capella.common.data.activity.ActivityNode}

Returns the Sequence of targets for the given FunctionalExchange.

### Parameter

* **value**: the FunctionalExchange

### Example

* myFunctionalExchange.getTarget()
  * the Sequence of targets for the given FunctionalExchange

## org.polarsys.capella.core.data.information.communication.CommunicationLink.getTarget() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem}

Returns the Sequence of target for the given CommunicationLink.

### Parameter

* **value**: the CommunicationLink

### Example

* myCommunicationLink.getTarget()
  * the Sequence of target for the given CommunicationLink

## org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend.getTarget() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of target for the given AbstractCapabilityExtend.

### Parameter

* **value**: the AbstractCapabilityExtend

### Example

* myAbstractCapabilityExtend.getTarget()
  * the Sequence of target for the given AbstractCapabilityExtend

## org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization.getTarget() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of target for the given AbstractCapabilityGeneralization.

### Parameter

* **value**: the AbstractCapabilityGeneralization

### Example

* myAbstractCapabilityGeneralization.getTarget()
  * the Sequence of target for the given AbstractCapabilityGeneralization

## org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude.getTarget() : Sequence{org.polarsys.capella.core.data.interaction.AbstractCapability}

Returns the Sequence of target for the given AbstractCapabilityInclude.

### Parameter

* **value**: the AbstractCapabilityInclude

### Example

* myAbstractCapabilityInclude.getTarget()
  * the Sequence of target for the given AbstractCapabilityInclude

## org.polarsys.capella.core.data.oa.CommunicationMean.getTarget() : Sequence{org.polarsys.capella.common.data.modellingcore.InformationsExchanger}

Returns the Sequence of target for the given CommunicationMean.

### Parameter

* **value**: the CommunicationMean

### Example

* myCommunicationMean.getTarget()
  * the Sequence of target for the given CommunicationMean

## org.polarsys.capella.core.data.capellacommon.StateTransition.getTrigger() : Sequence{org.polarsys.capella.common.data.behavior.AbstractEvent}

Returns the Sequence of trigger for the given StateTransition.

### Parameter

* **value**: the StateTransition

### Example

* myStateTransition.getTrigger()
  * the Sequence of trigger for the given StateTransition

## org.polarsys.capella.common.data.activity.Pin.getType() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractType}

Returns the Sequence of type for the given Pin.

### Parameter

* **value**: the Pin

### Example

* myPin.getType()
  * the Sequence of type for the given Pin

## org.polarsys.capella.core.data.cs.Part.getType() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractType}

Returns the Sequence of type for the given Part.

### Parameter

* **value**: the Part

### Example

* myPart.getType()
  * the Sequence of type for the given Part

## org.polarsys.capella.core.data.fa.ComponentPort.getType() : Sequence{org.polarsys.capella.core.data.capellacore.Type}

Returns the Sequence of types for the given ComponentPort.

### Parameter

* **value**: the ComponentPort

### Example

* myComponentPort.getType()
  * the Sequence of types for the given ComponentPort

## org.polarsys.capella.core.data.information.Collection.getType() : Sequence{org.polarsys.capella.core.data.capellacore.Type}

Returns the Sequence of type for the given Collection.

### Parameter

* **value**: the Collection

### Example

* myCollection.getType()
  * the Sequence of type for the given Collection

## org.polarsys.capella.core.data.information.ExchangeItemElement.getType() : Sequence{org.polarsys.capella.common.data.modellingcore.AbstractType}

Returns the Sequence of type for the given ExchangeItemElement.

### Parameter

* **value**: the ExchangeItemElement

### Example

* myExchangeItemElement.getType()
  * the Sequence of type for the given ExchangeItemElement

## org.polarsys.capella.core.data.information.Parameter.getType() : Sequence{org.polarsys.capella.core.data.capellacore.Type}

Returns the Sequence of type for the given Parameter.

### Parameter

* **value**: the Parameter

### Example

* myParameter.getType()
  * the Sequence of type for the given Parameter

## org.polarsys.capella.core.data.information.Property.getType() : Sequence{org.polarsys.capella.core.data.capellacore.Type}

Returns the Sequence of types for the given Property.

### Parameter

* **value**: the Property

### Example

* myProperty.getType()
  * the Sequence of types for the given Property

## org.polarsys.capella.core.data.capellacore.Type.getTypingElements() : Sequence{org.polarsys.capella.core.data.information.ExchangeItemElement}

Returns the Sequence of typing elements for the given Type.

### Parameter

* **value**: the Type

### Example

* myAllocation.getTypingElements()
  * the Sequence of typing elements for the given Type

## org.polarsys.capella.core.data.cs.Component.getUsedInterfaces() : Sequence{org.polarsys.capella.core.data.cs.Interface}

Returns the Sequence of used interfaces for the given Component.

### Parameter

* **value**: the Component

### Example

* myComponent.getUsedInterfaces()
  * the Sequence of used interfaces for the given Component

## org.polarsys.capella.core.data.cs.Interface.getUsers() : Sequence{org.polarsys.capella.core.data.cs.Component}

Returns the Sequence of users for the given Interface.

### Parameter

* **value**: the Interface

### Example

* myInterface.getUsers()
  * the Sequence of users for the given Interface

## org.polarsys.capella.core.data.capellacore.AbstractPropertyValue.getValuedElements() : Sequence{org.polarsys.capella.core.data.capellacore.CapellaElement}

Returns the Sequence of valued elements for the given AbstractPropertyValue.

### Parameter

* **value**: the AbstractPropertyValue

### Example

* myAbstractPropertyValue.getValuedElements()
  * the Sequence of valued elements for the given AbstractPropertyValue

## org.polarsys.capella.core.data.capellacore.PropertyValueGroup.getValuedElements() : Sequence{org.polarsys.capella.core.data.capellacore.CapellaElement}

Returns the Sequence of valued elements for the given PropertyValueGroup.

### Parameter

* **value**: the PropertyValueGroup

### Example

* myPropertyValueGroup.getValuedElements()
  * the Sequence of valued elements for the given PropertyValueGroup

## org.polarsys.capella.core.data.capellacore.AbstractPropertyValue.getValue() : Sequence{org.polarsys.capella.core.data.capellacore.AbstractPropertyValue}

Returns the Sequence of value for the given AbstractPropertyValue.

### Parameter

* **value**: the AbstractPropertyValue

### Example

* myAbstractPropertyValue.getValue()
  * the Sequence of value for the given AbstractPropertyValue

## org.polarsys.capella.core.data.information.datavalue.DataValue.getValue() : Sequence{String}

Returns the Sequence of values for the given DataValue.

### Parameter

* **value**: the DataValue

### Example

* myDataValue.getValue()
  * the Sequence of values for the given DataValue



