<?php

namespace App\Controller;

use App\Entity\Ingredient;
use App\Entity\Ingredientrecette;
use App\Form\IngredientrecetteType;
use App\Repository\IngredientrecetteRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/ingredientrecette")
 */
class IngredientrecetteController extends AbstractController
{
    /**
     * @Route("/", name="ingredientrecette_index", methods={"GET"})
     */
    public function index(Request $request): Response
    {
        $ingredientrecettes = $this->getDoctrine()->getRepository(Ingredientrecette::class)->findAll();
        return $this->render('ingredientrecette/index.html.twig', [
            'ingredientrecettes' => $ingredientrecettes,
        ]);
    }

    /**
     * @Route("/new", name="ingredientrecette_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $ingredientrecette = new Ingredientrecette();
        $form = $this->createForm(IngredientrecetteType::class, $ingredientrecette);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($ingredientrecette);
            $entityManager->flush();

            return $this->redirectToRoute('ingredientrecette_index');
        }

        return $this->render('ingredientrecette/new.html.twig', [
            'ingredientrecette' => $ingredientrecette,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="ingredientrecette_show", methods={"GET"})
     */
    public function show(Ingredientrecette $ingredientrecette): Response
    {
        return $this->render('ingredientrecette/show.html.twig', [
            'ingredientrecette' => $ingredientrecette,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="ingredientrecette_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Ingredientrecette $ingredientrecette): Response
    {
        $form = $this->createForm(IngredientrecetteType::class, $ingredientrecette);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('ingredientrecette_index');
        }

        return $this->render('ingredientrecette/edit.html.twig', [
            'ingredientrecette' => $ingredientrecette,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="ingredientrecette_delete", methods={"POST"})
     */
    public function delete(Request $request, Ingredientrecette $ingredientrecette): Response
    {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($ingredientrecette);
            $entityManager->flush();
        return $this->redirectToRoute('ingredientrecette_index',array('id'=>$ingredientrecette));
    }
}
