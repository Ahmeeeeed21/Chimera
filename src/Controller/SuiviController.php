<?php

namespace App\Controller;

use App\Entity\Suivi;
use App\Entity\Utilisateur;
use App\Form\SuiviType;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Validator\Constraints\DateTime;
/**
 * @Route("/suivi")
 */
class SuiviController extends Controller
{
    /**
     * @Route("/", name="suivi_index", methods={"GET"})
     */
    public function index(Request $request): Response
    {
        $suivis = $this->getDoctrine()
            ->getRepository(Suivi::class)
            ->findBy(['idutilisateur'=>1]);

        $pagerecette = $this->get('knp_paginator')->paginate(
            $suivis, $request->query->getInt('page', 1), 3);

        return $this->render('suivi/index.html.twig', [
            'suivis' => $pagerecette,
        ]);
    }

    /**
     * @Route("/new", name="suivi_new", methods={"GET","POST"})
     * @param Request $request
     * @param $user
     * @return Response
     */
    public function new(Request $request,FlashyNotifier $flashy): Response
    {
        $suivi = new Suivi();
        $suivi->setDate(new \DateTime());
        $suivi->setIdutilisateur(1);
        $form = $this->createForm(SuiviType::class, $suivi);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $suivi->setTaille($suivi->getTaille()/100);
            $entityManager->persist($suivi);
            $entityManager->flush();
            $flashy->success('Votre Suivi a été ajouté');
            return $this->redirectToRoute('suivi_index');
        }

        return $this->render('suivi/new.html.twig', [
            'suivi' => $suivi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="suivi_show", methods={"GET"})
     */
    public function show(Suivi $suivi): Response
    {
        return $this->render('suivi/show.html.twig', [
            'suivi' => $suivi,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="suivi_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Suivi $suivi): Response
    {
        $suivi->setDate(new \DateTime());
        $form = $this->createForm(SuiviType::class, $suivi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('suivi_index');
        }

        return $this->render('suivi/edit.html.twig', [
            'suivi' => $suivi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="suivi_delete", methods={"POST"})
     */
    public function delete(Request $request, Suivi $suivi): Response
    {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($suivi);
            $entityManager->flush();
        return $this->redirectToRoute('suivi_index');
    }
}
