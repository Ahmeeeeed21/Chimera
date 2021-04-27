<?php

namespace App\Form;

use App\Entity\Recette;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Vich\UploaderBundle\Form\Type\VichImageType;
class RecetteType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomrecette',TextType::class, ['attr' => ['placeholder' => 'nom', 'class' => 'form-control pname'],])
            ->add('typerecette',ChoiceType::class,[
                'choices' => [
                    'Type de Recette' => [
                        'Please select a type'=>'vide',
                        'Entrée' => 'Entrée',
                        'Plat' =>'Plat',
                        'Dessert' => 'Dessert',
                        'Amuse bouche' => 'Amuse bouche',
                        'Sauce' => 'Sauce',
                        'Accompagnement' => 'Accompagnement',
                        'Boisson' =>'Boisson',
                    ],

                ],])
            ->add('image_File',FileType::class,['attr' =>['placeholder' => 'Select Image'],])
            ->add('description',TextareaType::class,['attr' => ['placeholder' => 'Description'],])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Recette::class,
        ]);
    }
}
